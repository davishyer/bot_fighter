package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.exceptions.InvalidRequestException
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.awt.Point
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.schedule

@JsonSerialize(using = GameSerializer::class)
class Game constructor(
  private val gameStore: GameStore
) {
  companion object {
    const val WIDTH: Int = 10
    const val HEIGHT: Int = 4
    const val MAX_PLAYERS: Int = 2
    const val TICK_DURATION: Long = 1 // in seconds
    const val TICK_LIMIT: Int = 60

    val COUNT: AtomicInteger = AtomicInteger()
  }
  var id: Int = Game.COUNT.incrementAndGet()
  private var tickCount: Int = 0
  var players: MutableCollection<Player> = mutableListOf()


  fun addPlayer(name: String) {
    this.players.add(Player(getStartingPoint(), name, getStartingDirection()))
    if (this.players.size == Game.MAX_PLAYERS) {
      fixedRateTimer("Game $this.id", false, 0L, Game.TICK_DURATION * 1000) {
        tick(this)
      }
    }
  }

  fun queueMoves(playerId: String, moves: Collection<GameMove>) {
    val player = this.players.find { playerId == it.id } ?: throw InvalidRequestException("Invalid Player Id: $playerId")
    moves.forEach { player.enqueueMove(it) }
  }

  fun endGame() {
    val gameId = this.id
    println("Marking game $gameId for deletion")
    Timer("Ending game $gameId", false).schedule(10 * 1000) {
      gameStore.removeGame(gameId)
    }
  }

  fun timeRemaining() = (Game.TICK_LIMIT * Game.TICK_DURATION) - this.tickCount

  fun getWinner() = null

  private fun getStartingPoint(): Array<Point> {
    return when (this.players.size) {
      0 -> arrayOf(Point(0, 0), Point(0, 1))
      1 -> arrayOf(Point(Game.WIDTH - 1, 0), Point(Game.WIDTH - 1, 1))
      else -> throw InvalidRequestException("The game is already full")
    }
  }

  private fun getStartingDirection(): Boolean {
    return this.players.size == 0
  }

  private fun tick(timerTask: TimerTask) {
    println("tick called for game $id")
    val actions = this.players.mapNotNull { it.getNextAction() }.sortedBy { it.createdAt }
    actions.forEach { action ->
      val opponent = this.players.find { it.id != action.player.id } ?: throw RuntimeException("Could not find opponent player object")
      action.execute(opponent)
    }

    faceOpponent(this.players.elementAt(0), this.players.elementAt(1))
    faceOpponent(this.players.elementAt(1), this.players.elementAt(0))

    this.tickCount += 1
    if (this.tickCount == Game.TICK_LIMIT) {
      println("ending game $id")
      timerTask.cancel()
      endGame()
    }
  }

  private fun faceOpponent(player: Player, opponent: Player) {
    if (player.location.elementAt(0).y == 0 && player.location.elementAt(0).x != opponent.location.elementAt(0).x) {
      player.isFacingRight = player.location.elementAt(0).x < opponent.location.elementAt(0).x
    }
  }
}
