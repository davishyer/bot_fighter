package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Player
import com.dhyer.bot_fighter.PlayerState
import java.awt.Point
import java.time.LocalDateTime

class BlockGameAction(player: Player, createdAt: LocalDateTime) : GameAction(player, createdAt) {
  override fun execute(opponent: Player) {
    this.player.state = if (this.player.state == PlayerState.Blocking) PlayerState.Idle else PlayerState.Blocking
    println("\t${if (this.player.state == PlayerState.Blocking) "Blocking" else "Unblocking"} player: ${this.player.id}")
  }
}
