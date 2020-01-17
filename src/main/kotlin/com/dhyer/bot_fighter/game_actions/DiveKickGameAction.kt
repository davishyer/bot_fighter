package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Game
import com.dhyer.bot_fighter.Player
import com.dhyer.bot_fighter.PlayerState
import java.awt.Point
import java.time.LocalDateTime

class DiveKickGameAction(player: Player, createdAt: LocalDateTime) : GameAction(player, createdAt) {
  override fun execute(opponent: Player) {
    println("\tDivekicking player: ${this.player.id}")
    println("\t\tStarting at: ${this.player.printLocation()}")
    val side = if (player.isFacingRight) 1 else -1
    this.player.location.forEach {
      it.x = Math.max(0, Math.min(it.x + side, Game.WIDTH))
      it.y -= 1
    }
    val hitbox = Point(side, 0)
    if (this.player.hitTestOpponent(opponent, hitbox) != null) {
      if (opponent.state != PlayerState.Blocking) {
        val damage = 20
        opponent.takeDamage(damage)
        println("\t${opponent.id} takes $damage damage!")
      } else {
        println("\t${opponent.id} blocks the kick")
      }
    }
    println("\t\tEnded at: ${this.player.printLocation()}")
    println("\t\tHitbox at: $hitbox")
  }
}
