package com.dhyer.bot_fighter

import org.springframework.stereotype.Service
import java.util.*

@Service
class GameStore {
  private val games: MutableMap<UUID, Game> = HashMap()

  fun createGame(): UUID {
    val game = Game()
    this.games[game.id] = game
    return game.id
  }

  fun getGames(): Collection<Game> {
    return this.games.values
  }
}