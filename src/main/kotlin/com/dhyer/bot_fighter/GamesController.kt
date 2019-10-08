package com.dhyer.bot_fighter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

data class CreateGameResponse(
  val gameId: UUID
)

data class ShowGamesResponse(
  val games: Collection<Game>
)

@RestController
@RequestMapping("/games")
class GamesController @Autowired constructor(
  val gameStore: GameStore
) {
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun create(): CreateGameResponse = CreateGameResponse(gameStore.createGame())

  @GetMapping
  fun index(): ShowGamesResponse = ShowGamesResponse(gameStore.getGames())
}