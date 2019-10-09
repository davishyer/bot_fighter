package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.game_actions.GameAction
import java.awt.Point

class Player constructor(position: Array<Point>, name: String) {
//  var id: UUID = UUID.randomUUID()
  var id: String = name
  var health: Long = 100
  private var moveQueue: Queue<GameMove> = Queue()
  private var actionQueue: Queue<GameAction> = Queue()

  fun enqueueMove(move: GameMove) {
    // TODO queue size limit, move validation
    this.moveQueue.enqueue(move)
  }

  fun getNextAction(): GameAction? {
    if (actionQueue.isEmpty()) {
      GameActionFactory.generateActionsFor(moveQueue.dequeue()).forEach {
        actionQueue.enqueue(it)
      }
    }
    return actionQueue.dequeue()
  }
}
