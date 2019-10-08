package com.dhyer.bot_fighter

import java.awt.Point
import java.util.*

class Player constructor(position: Array<Point>, name: String) {
//  var id: UUID = UUID.randomUUID()
  var id: String = name
  var health: Long = 100
  private var moveQueue: Queue<GameMove> = Queue()

  fun enqueueMove(move: GameMove) {
    // TODO queue size limit, move validation
    this.moveQueue.enqueue(move)
  }
}