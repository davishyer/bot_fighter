package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Player
import com.dhyer.bot_fighter.PlayerState
import java.awt.Point
import java.time.LocalDateTime

class AttackGameAction(player: Player, createdAt: LocalDateTime) : GameAction(player, createdAt) {
  override fun execute(opponent: Player) {
    player.state = PlayerState.Attacking

    if (isHit(opponent)) {
      println("\tAttacking player: ${this.player.id}")
      // TODO blocked or damage
    } else {
      println("\tPlayer ${this.player.id} attacked but missed!")
    }
  }

  fun isHit(opponent: Player): Boolean {
    val x = getSingleXCoord(player)
    val opponentX = getSingleXCoord(opponent)
    val side = if (player.isFacingRight) 1 else -1

    val isWithinRange = x + side == opponentX
    val isOverTheHead = !player.isCrouched() && opponent.isCrouched()

    return isWithinRange && !isOverTheHead
  }

  fun getSingleXCoord(player: Player): Int {
    return player.location.map { it.x }.min() ?: 0
  }
}
