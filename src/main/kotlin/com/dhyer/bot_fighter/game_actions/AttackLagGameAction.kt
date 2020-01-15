package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Player
import com.dhyer.bot_fighter.PlayerState
import java.time.LocalDateTime

class AttackLagGameAction(player: Player, createdAt: LocalDateTime) : GameAction(player, createdAt) {
  override fun execute(opponent: Player) {
    player.state = PlayerState.Idle
    println("\tAttack lag: ${this.player.id}")
  }
}
