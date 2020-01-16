package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.game_actions.GameAction
import com.dhyer.bot_fighter.PlayerState
import java.awt.Point
import java.util.*

class Player constructor(position: Array<Point>, name: String, isFacingRight: Boolean) {
  companion object {
    const val HEIGHT: Int = 2
    const val MAX_QUEUED_MOVES: Int = 10
  }
//  var id: UUID = UUID.randomUUID()
  var id: String = name
  var health: Long = 100
  var location: MutableCollection<Point> = position.toMutableList()
  var state: PlayerState = PlayerState.Idle
  var isFacingRight: Boolean = isFacingRight
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

  fun hitTestOpponent(opponent: Player, hitbox: Point): Point? {
    // hitbox point is relative to player's location at index 0
    val x = this.location.elementAt(0).x + hitbox.x
    val y = this.location.elementAt(0).y + hitbox.y
    return opponent.location.find { it.x == x && it.y == y }
  }

  fun takeDamage(damage: Int) {
    this.health = Math.max(0, this.health - damage)
  }

  fun printLocation(): String {
    return Arrays.toString(this.location.toTypedArray())
  }
}
