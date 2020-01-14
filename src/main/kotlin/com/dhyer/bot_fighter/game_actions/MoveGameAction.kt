package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Player
import java.time.LocalDateTime

class MoveGameAction(player: Player, createdAt: LocalDateTime) : GameAction(player, createdAt) {
  override fun execute() {
    // TODO frd move players
    println("\tMoving player")
  }
}
