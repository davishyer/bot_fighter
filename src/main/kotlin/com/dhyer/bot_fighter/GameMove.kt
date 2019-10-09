package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.exceptions.InvalidRequestException
import com.fasterxml.jackson.annotation.JsonCreator

enum class GameMoveDirection constructor(private val text: String) {
  UP("up"),
  RIGHT("right"),
  DOWN("down"),
  LEFT("left");

  companion object {
    @JsonCreator
    @JvmStatic
    fun fromText(text: String): GameMoveDirection {
      for(direction: GameMoveDirection in GameMoveDirection.values()) {
        if (direction.text == text) return direction
      }
      // TODO better error handling for the response body
      throw InvalidRequestException("Unrecognized direction: $text. Valid directions are 'up', 'right', 'down', and 'left'.")
    }
  }
}

enum class GameMoveButton constructor(private val text: String) {
  ATTACK("attack"),
  BLOCK("block");

  companion object {
    @JsonCreator
    @JvmStatic
    fun fromText(text: String): GameMoveButton {
      for(button: GameMoveButton in GameMoveButton.values()) {
        if (button.text == text) return button
      }
      // TODO better error handling for the response body
      throw InvalidRequestException("Unrecognized button: $text. Supported buttons are 'attack' and 'block'.")
    }
  }
}

class GameMove constructor(
  private val direction: GameMoveDirection?,
  private val button: GameMoveButton?
) {
  override fun toString(): String {
    return directionToString() + buttonToString()
  }

  private fun directionToString(): String {
    return when (this.direction) {
      GameMoveDirection.UP -> "↑"
      GameMoveDirection.RIGHT -> "→"
      GameMoveDirection.DOWN -> "↓"
      GameMoveDirection.LEFT -> "←"
      null -> ""
    }
  }

  private fun buttonToString(): String {
    return when (this.button) {
      GameMoveButton.ATTACK -> "A"
      GameMoveButton.BLOCK -> "B"
      null -> ""
    }
  }
}
