package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.game_actions.GameAction
import com.dhyer.bot_fighter.PlayerState
import java.awt.Point
import java.util.*

class Player constructor(position: Array<Point>, name: String) {
  companion object {
    const val HEIGHT: Int = 2
    const val MAX_QUEUED_MOVES: Int = 10
  }
//  var id: UUID = UUID.randomUUID()
  var id: String = name
  var health: Long = 100
  var location: MutableCollection<Point> = position.toMutableList()
  var state: PlayerState = PlayerState.Idle
  var isFacingRight: Boolean = true
  private var moveQueue: Queue<GameMove> = Queue()
  private var actionQueue: Queue<GameAction> = Queue()

  fun enqueueMove(move: GameMove) {
    if(this.moveQueue.count() < Player.MAX_QUEUED_MOVES) {
      this.moveQueue.enqueue(move)
    }
  }

  fun getNextAction(): GameAction? {
    if (actionQueue.isEmpty()) {
      GameActionFactory.generateActionsFor(moveQueue.dequeue(), this).forEach {
        actionQueue.enqueue(it)
      }
    }
    return actionQueue.dequeue()
  }

  fun isCrouched(): Boolean {
    // assume that one location point means
    // the player is crouched
    return this.location.size == 1
  }

  fun printLocation(): String {
    return Arrays.toString(this.location.toTypedArray())
  }
}
