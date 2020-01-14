package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Player

class FallGameAction(private val player: Player) : GameAction {
  override fun execute() {
    println("\tFalling player: ${this.player.id}")
    println("\t\tStarting at: ${this.player.printLocation()}")
    // Shift all player location points down one on the
    // game board
    this.player.location.forEach { it.y -= 1 }
    println("\t\tEnded at: ${this.player.printLocation()}")
  }
}
