package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.exceptions.InvalidRequestException
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameStore {
  private val games: MutableMap<Int, Game> = HashMap()

  fun createGame(): Int {
    val game = Game()
    game.addPlayer("Foo")
    game.addPlayer("Bar")
    this.games[game.id] = game
    return game.id
  }

  fun getGames(): Collection<Game> {
    return this.games.values
  }

  fun find(id: Int): Game {
    return this.games[id] ?: throw InvalidRequestException("The requested game does not exist: $id")
  }
}
