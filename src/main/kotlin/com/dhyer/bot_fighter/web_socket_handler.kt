package com.dhyer.bot_fighter

import com.fasterxml.jackson.databind.ObjectMapper
import org.eclipse.jetty.websocket.api.Session
import org.eclipse.jetty.websocket.api.annotations.*
import java.util.concurrent.atomic.AtomicLong

fun main(args: Array<String>) {
  port(9000)
  staticFileLocation("/public")
  webSocket("/ws", WebSocketHandler::class.java)
  init()
}

@WebSocket
class WebSocketHandler {

  val players = HashMap<Session, Player>()
  var lastPlayerID = AtomicLong(0)

  @OnWebSocketConnect
  fun connected(session: Session) = println("session connected")

  @OnWebSocketClose
  fun closed(session: Session, statusCode: Int, reason: String?) {
  }

  @OnWebSocketMessage
  fun message(session: Session, json: String) {
    val JSON = ObjectMapper().readTree(json)
    val message = JSON.get("msg").asText()

    when (message) {
      "newplayer" -> {
      }
      "click" -> {
      }
      "test" -> println("test received")
    }
    println("Got: $message")
  }
}
