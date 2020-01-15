package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Game
import com.dhyer.bot_fighter.Player
import java.awt.Point
import java.time.LocalDateTime

class MoveGameAction(player: Player, createdAt: LocalDateTime, val vector: Int) : GameAction(player, createdAt) {
  override fun execute(opponent: Player) {
    val x = getSingleXCoord(player)
    val opponentX = getSingleXCoord(opponent)
    if (x + vector < 0) {
      println("\tCannot move into left wall")
    } else if (x + vector > Game.WIDTH) {
      println("\tCannot move into right wall")
    } else if (x + vector == opponentX) {
      println("\tCannot move into opponent")
    } else {
      player.location.forEach { it.x += vector }
      println("\tMoving player ${this.player.id}")
    }
  }

  fun getSingleXCoord(player: Player): Int {
    return player.location.map { it.x }.min() ?: 0
  }
}
