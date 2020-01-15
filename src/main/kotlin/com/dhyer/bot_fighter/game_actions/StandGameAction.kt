package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Player
import java.awt.Point
import java.time.LocalDateTime

class StandGameAction(player: Player, createdAt: LocalDateTime) : GameAction(player, createdAt) {
  override fun execute(opponent: Player) {
    println("\tStanding player: ${this.player.id}")
    println("\t\tStarting at: ${this.player.printLocation()}")
    val currentX = this.player.location.elementAt(0).x
    this.player.location.add(Point(currentX, 1))
    println("\t\tEnded at: ${this.player.printLocation()}")
  }
}
