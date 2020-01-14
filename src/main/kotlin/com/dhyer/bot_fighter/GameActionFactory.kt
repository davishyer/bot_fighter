package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.game_actions.CrouchGameAction
import com.dhyer.bot_fighter.game_actions.FallGameAction
import com.dhyer.bot_fighter.game_actions.GameAction
import com.dhyer.bot_fighter.game_actions.JumpGameAction
import com.dhyer.bot_fighter.game_actions.StandGameAction
import java.time.LocalDateTime

class GameActionFactory {
  companion object {
    fun generateActionsFor(move: GameMove?, player: Player): List<GameAction> {
      return when (move?.toString()) {
        "↑" -> handleUp(player, move.createdAt)
        "→" -> handleRight()
        "↓" -> handleDown(player, move.createdAt)
        "←" -> handleLeft()
        "A" -> handleAttack()
        "B" -> handleBlock()
        "↑A" -> handleUpAttack()
        "↑B" -> handleUpBlock()
        "→A" -> handleRightAttack()
        "→B" -> handleRightBlock()
        "↓A" -> handleDownAttack()
        "↓B" -> handleDownBlock()
        "←A" -> handleLeftAttack()
        "←B" -> handleLeftBlock()
        else -> emptyList()
      }
    }

    private fun handleUp(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling up")
      return if(player.isCrouched()) {
        listOf(StandGameAction(player, timestamp))
      } else {
        listOf(
          JumpGameAction(player, timestamp),
          // TODO need to fall more if the board height changes
          FallGameAction(player, timestamp),
          FallGameAction(player, timestamp)
        )
      }
    }
    private fun handleRight(): List<GameAction> {
      // TODO implement
      println("handling right")
      return emptyList()
    }
    private fun handleDown(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling down")
      return listOf(CrouchGameAction(player, timestamp))
    }
    private fun handleLeft(): List<GameAction> {
      // TODO implement
      println("handling left")
      return emptyList()
    }
    private fun handleAttack(): List<GameAction> {
      // TODO implement
      println("handling attack")
      return emptyList()
    }
    private fun handleBlock(): List<GameAction> {
      // TODO implement
      println("handling block")
      return emptyList()
    }
    private fun handleUpAttack(): List<GameAction> {
      // TODO implement
      println("handling up attack")
      return emptyList()
    }
    private fun handleUpBlock(): List<GameAction> {
      // TODO implement
      println("handling up block")
      return emptyList()
    }
    private fun handleRightAttack(): List<GameAction> {
      // TODO implement
      println("handling right attack")
      return emptyList()
    }
    private fun handleRightBlock(): List<GameAction> {
      // TODO implement
      println("handling right block")
      return emptyList()
    }
    private fun handleDownAttack(): List<GameAction> {
      // TODO implement
      println("handling down attack")
      return emptyList()
    }
    private fun handleDownBlock(): List<GameAction> {
      // TODO implement
      println("handling down block")
      return emptyList()
    }
    private fun handleLeftAttack(): List<GameAction> {
      // TODO implement
      println("handling left attack")
      return emptyList()
    }
    private fun handleLeftBlock(): List<GameAction> {
      // TODO implement
      println("handling left block")
      return emptyList()
    }
  }
}
