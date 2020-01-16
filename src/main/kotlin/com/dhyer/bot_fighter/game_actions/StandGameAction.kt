package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Player
import com.dhyer.bot_fighter.PlayerState
import java.awt.Point
import java.time.LocalDateTime

class StandGameAction(player: Player, createdAt: LocalDateTime) : GameAction(player, createdAt) {
  override fun execute(opponent: Player) {
    println("\tStanding player: ${this.player.id}")
    println("\t\tStarting at: ${this.player.printLocation()}")
    val currentX = this.player.location.elementAt(0).x
    this.player.location.add(Point(currentX, 1))
    if (this.player.state != PlayerState.Blocking) {
      this.player.state = PlayerState.Idle
    }
    println("\t\tEnded at: ${this.player.printLocation()}")
  }
}
