package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Player
import com.dhyer.bot_fighter.PlayerState
import java.awt.Point
import java.time.LocalDateTime

class AttackGameAction(player: Player, createdAt: LocalDateTime) : GameAction(player, createdAt) {
  override fun execute(opponent: Player) {
    player.state = PlayerState.Attacking
    val side = if (player.isFacingRight) 1 else -1
    val hitbox = Point(side, if (player.isCrouched()) 0 else 1)

    if (player.hitTestOpponent(opponent, hitbox) != null) {
      println("\tAttacking player: ${this.player.id}")
      if (opponent.state != PlayerState.Blocking) {
        val damage = 20
        opponent.takeDamage(damage)
        println("\t${opponent.id} takes $damage damage!")
      } else {
        println("\t${opponent.id} blocks the attack")
      }
    } else {
      println("\tPlayer ${this.player.id} attacked but missed!")
    }
  }
}
