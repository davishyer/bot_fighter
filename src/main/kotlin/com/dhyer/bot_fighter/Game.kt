package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.exceptions.InvalidRequestException
import java.awt.Point
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.schedule

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
  var tickCount: Int = 0
  private var players: MutableCollection<Player> = mutableListOf()


  fun addPlayer(name: String) {
    this.players.add(Player(getStartingPoint(), name))
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

  private fun getStartingPoint(): Array<Point> {
    return when (this.players.size) {
      0 -> arrayOf(Point(0, 0), Point(0, 1))
      1 -> arrayOf(Point(Game.WIDTH - 1, 0), Point(Game.WIDTH - 1, 1))
      else -> throw InvalidRequestException("The game is already full")
    }
  }

  private fun tick(timerTask: TimerTask) {
    println("tick called for game $id")
    val actions = this.players.mapNotNull { it.getNextAction() }.sortedBy { it.createdAt }
    actions.forEach { it.execute() }
    this.tickCount += 1
    if (this.tickCount == Game.TICK_LIMIT) {
      println("ending game $id")
      timerTask.cancel()
      endGame()
    }
  }
}
