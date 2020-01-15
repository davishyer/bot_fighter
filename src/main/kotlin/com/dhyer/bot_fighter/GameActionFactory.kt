package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.game_actions.AttackGameAction
import com.dhyer.bot_fighter.game_actions.AttackLagGameAction
import com.dhyer.bot_fighter.game_actions.BlockGameAction
import com.dhyer.bot_fighter.game_actions.CrouchGameAction
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
