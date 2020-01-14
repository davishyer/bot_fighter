package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Player
import java.awt.Point

class StandGameAction(private val player: Player) : GameAction {
  override fun execute() {
    println("\tStanding player: ${this.player.id}")
    println("\t\tStarting at: ${this.player.printLocation()}")
    val currentX = this.player.location.elementAt(0).x
    this.player.location.add(Point(currentX, 1))
    println("\t\tEnded at: ${this.player.printLocation()}")
  }
}
