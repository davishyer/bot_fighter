package com.dhyer.bot_fighter.game_actions

import com.dhyer.bot_fighter.Player
import java.time.LocalDateTime

abstract class GameAction(val player: Player, val createdAt: LocalDateTime) {
  abstract fun execute(opponent: Player)
}
