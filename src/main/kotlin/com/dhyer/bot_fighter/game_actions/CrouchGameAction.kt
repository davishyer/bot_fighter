package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Player
import java.time.LocalDateTime

class CrouchGameAction(player: Player, createdAt: LocalDateTime) : GameAction(player, createdAt) {
  override fun execute() {
    println("\tCrouching player: ${this.player.id}")
    println("\t\tStarting at: ${this.player.printLocation()}")
    // remove all player positions, aside from the bottom row
    this.player.location.removeIf { it.y != 0 }
    println("\t\tEnded at: ${this.player.printLocation()}")
  }
}
