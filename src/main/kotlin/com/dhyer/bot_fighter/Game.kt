package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.exceptions.InvalidRequestException
import sun.audio.AudioPlayer.player
import java.awt.Point
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.Collectors
import kotlin.concurrent.fixedRateTimer

class Game {
  companion object {
    const val WIDTH: Int = 10
    const val HEIGHT: Int = 4
    const val MAX_PLAYERS: Int = 2

    val COUNT: AtomicInteger = AtomicInteger()
  }
  var id: Int = Game.COUNT.incrementAndGet()
  private var players: MutableCollection<Player> = mutableListOf()


  fun addPlayer(name: String) {
    this.players.add(Player(getStartingPoint(), name))
    if (this.players.size == Game.MAX_PLAYERS) {
      fixedRateTimer("Game $this.id", false, 0L, 1 * 1000) {
        tick(this)
      }
    }
  }

  fun queueMoves(playerId: String, moves: Collection<GameMove>) {
    val player = this.players.find { playerId == it.id } ?: throw InvalidRequestException("Invalid Player Id: $playerId")
    moves.forEach { player.enqueueMove(it) }
  }

  private fun getStartingPoint(): Array<Point> {
    return when (this.players.size) {
      0 -> arrayOf(Point(0, Game.HEIGHT - 2), Point(0, Game.HEIGHT - 1))
      1 -> arrayOf(Point(Game.WIDTH - 1, Game.HEIGHT - 2), Point(Game.WIDTH - 1, Game.HEIGHT - 1))
      else -> throw InvalidRequestException("The game is already full")
    }
  }

  private fun tick(timerTask: TimerTask) {
    println("tick called for game $id")
    val actions = this.players.mapNotNull { it.getNextAction() }
    actions.forEach { it.execute() }
    if (id != Game.COUNT.get()) timerTask.cancel()
  }
}
