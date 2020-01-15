package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.exceptions.InvalidRequestException
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameStore {
  private val games: MutableMap<Int, Game> = HashMap()

  companion object {
    const val GAME_FETCH_LIMIT: Int = 10
  }

  fun createGame(): Int {
    val game = Game(this)
    game.addPlayer("Foo")
    game.addPlayer("Bar")
    this.games[game.id] = game
    return game.id
  }

  // returns the N (GAME_FETCH_LIMIT) most recently created games
  fun getGames(): Collection<Game> {
    return this.games.values.sortedByDescending { it.id }.take(GameStore.GAME_FETCH_LIMIT)
  }

  fun find(id: Int): Game {
    return this.games[id] ?: throw InvalidRequestException("The requested game does not exist: $id")
  }

  fun removeGame(id: Int) {
    println("Removing game $id")
    this.games.remove(id)
  }

  fun clear() {
    this.games.clear()
  }
}
