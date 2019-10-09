package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.game_actions.GameAction
import com.dhyer.bot_fighter.game_actions.MoveGameAction

class GameActionFactory {
  companion object {
    fun generateActionsFor(move: GameMove?): List<GameAction> {
      // TODO frd generate actions for move
      return listOf(MoveGameAction())
    }
  }
}
