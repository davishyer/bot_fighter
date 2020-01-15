package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.annotations.Private
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

data class CreateGameResponse(
  val gameId: Int
)

data class ShowGamesResponse(
  val games: Collection<Game>
)

data class QueueMovesRequest(
  val id: String,
  val moves: Collection<GameMove>
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
  // TODO: hide server bot games by query param
  fun index(): ShowGamesResponse = ShowGamesResponse(gameStore.getGames())

  @PostMapping("/{gameId}/move")
  fun queueMove(
    @PathVariable gameId: Int,
    @RequestBody movesToQueue: QueueMovesRequest
  ) {
    gameStore.find(gameId).queueMoves(movesToQueue.id, movesToQueue.moves)
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Private
  fun clearGames() {
    gameStore.clear()
  }
}
