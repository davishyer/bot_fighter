package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.exceptions.InvalidRequestException
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameStore {
  private val games: MutableMap<UUID, Game> = HashMap()

  fun createGame(): UUID {
    val game = Game()
    game.addPlayer("Foo")
    game.addPlayer("Bar")
    this.games[game.id] = game
    return game.id
  }

  fun getGames(): Collection<Game> {
    return this.games.values
  }

  fun find(id: UUID): Game {
    return this.games[id] ?: throw InvalidRequestException("The requested game does not exist: $id")
  }
}