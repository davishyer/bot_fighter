package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.exceptions.InvalidRequestException
import java.awt.Point
import java.util.*

class Game {
  companion object {
    const val WIDTH: Int = 10
    const val HEIGHT: Int = 4
  }
  var id: UUID = UUID.randomUUID()
  private var players: MutableCollection<Player> = mutableListOf()

  fun addPlayer(name: String) {
    this.players.add(Player(getStartingPoint(), name))
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
}