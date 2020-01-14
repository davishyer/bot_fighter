package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.game_actions.GameAction
import java.awt.Point
import java.util.*

class Player constructor(position: Array<Point>, name: String) {
  companion object {
    const val HEIGHT: Int = 2
  }
//  var id: UUID = UUID.randomUUID()
  var id: String = name
  var health: Long = 100
  var location: MutableCollection<Point> = position.toMutableList()
  private var moveQueue: Queue<GameMove> = Queue()
  private var actionQueue: Queue<GameAction> = Queue()

  fun enqueueMove(move: GameMove) {
    // TODO queue size limit, move validation
    this.moveQueue.enqueue(move)
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
