package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Game
import com.dhyer.bot_fighter.Player

class JumpGameAction(private val player: Player) : GameAction {
  override fun execute() {
    println("\tJumping player: ${this.player.id}")
    println("\t\tStarting at: ${this.player.printLocation()}")
    // Shift all player location points to the top of the
    // game board. Player height is subtracted to fit the
    // player on the board
    this.player.location.forEach { it.y += (Game.HEIGHT - Player.HEIGHT) }
    println("\t\tEnded at: ${this.player.printLocation()}")
  }
}
