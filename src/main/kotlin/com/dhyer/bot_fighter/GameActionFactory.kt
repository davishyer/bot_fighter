package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.game_actions.AttackGameAction
import com.dhyer.bot_fighter.game_actions.AttackLagGameAction
import com.dhyer.bot_fighter.game_actions.BlockGameAction
import com.dhyer.bot_fighter.game_actions.CrouchGameAction
import com.dhyer.bot_fighter.game_actions.DiveKickGameAction
import com.dhyer.bot_fighter.game_actions.FallGameAction
import com.dhyer.bot_fighter.game_actions.GameAction
import com.dhyer.bot_fighter.game_actions.JumpGameAction
import com.dhyer.bot_fighter.game_actions.MoveGameAction
import com.dhyer.bot_fighter.game_actions.StandGameAction
import java.time.LocalDateTime

class GameActionFactory {
  companion object {
    fun generateActionsFor(move: GameMove?, player: Player): List<GameAction> {
      return when (move?.toString()) {
        "↑" -> handleUp(player, move.createdAt)
        "→" -> handleRight(player, move.createdAt)
        "↓" -> handleDown(player, move.createdAt)
        "←" -> handleLeft(player, move.createdAt)
        "A" -> handleAttack(player, move.createdAt)
        "B" -> handleBlock(player, move.createdAt)
        "↑A" -> handleUpAttack(player, move.createdAt)
        "↑B" -> handleUpBlock(player, move.createdAt)
        "→A" -> handleRightAttack(player, move.createdAt)
        "→B" -> handleRightBlock(player, move.createdAt)
        "↓A" -> handleDownAttack(player, move.createdAt)
        "↓B" -> handleDownBlock(player, move.createdAt)
        "←A" -> handleLeftAttack(player, move.createdAt)
        "←B" -> handleLeftBlock(player, move.createdAt)
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
    private fun handleRight(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling right")
      return if(player.isCrouched()) {
        listOf(
          StandGameAction(player, timestamp),
          MoveGameAction(player, timestamp, 1)
        )
      } else {
        listOf(MoveGameAction(player, timestamp, 1))
      }
    }
    private fun handleDown(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling down")
      return listOf(CrouchGameAction(player, timestamp))
    }
    private fun handleLeft(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling left")
      return if(player.isCrouched()) {
        listOf(
          StandGameAction(player, timestamp),
          MoveGameAction(player, timestamp, -1)
        )
      } else {
        listOf(MoveGameAction(player, timestamp, -1))
      }
    }
    private fun handleAttack(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling attack")
      return listOf(
        StandGameAction(player, timestamp),
        AttackGameAction(player, timestamp),
        AttackLagGameAction(player, timestamp)
      )
    }
    private fun handleBlock(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling block")
      return listOf(BlockGameAction(player, timestamp))
    }
    private fun handleUpAttack(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling up attack")
      return if (player.isCrouched()) {
        handleAttack(player, timestamp)
      } else {
        listOf(
          JumpGameAction(player, timestamp),
          DiveKickGameAction(player, timestamp),
          DiveKickGameAction(player, timestamp),
          StandGameAction(player, timestamp)
        )
      }
    }
    private fun handleUpBlock(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling up block")
      return handleUp(player, timestamp) + handleBlock(player, timestamp)
    }
    private fun handleRightAttack(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling right attack")
      return handleRight(player, timestamp) + handleAttack(player, timestamp)
    }
    private fun handleRightBlock(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling right block")
      return handleRight(player, timestamp) + handleBlock(player, timestamp)
    }
    private fun handleDownAttack(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling down attack")
      return handleDown(player, timestamp) + handleAttack(player, timestamp)
    }
    private fun handleDownBlock(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling down block")
      return handleDown(player, timestamp) + handleBlock(player, timestamp)
    }
    private fun handleLeftAttack(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling left attack")
      return handleLeft(player, timestamp) + handleAttack(player, timestamp)
    }
    private fun handleLeftBlock(player: Player, timestamp: LocalDateTime): List<GameAction> {
      println("handling left block")
      return handleLeft(player, timestamp) + handleBlock(player, timestamp)
    }
  }
}
