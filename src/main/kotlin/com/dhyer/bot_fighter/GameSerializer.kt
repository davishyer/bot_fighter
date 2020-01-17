package com.dhyer.bot_fighter

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer

class GameSerializer : StdSerializer<Game>(Game::class.java) {
  override fun serialize(value: Game?, gen: JsonGenerator?, provider: SerializerProvider?) {
    gen?.writeStartObject()
    gen?.writeNumberField("id", value?.id!!)
    gen?.writeArrayFieldStart("players")
    value?.players!!.forEach {
      gen?.writeStartObject()
      // TODO use frd name, not id
      gen?.writeStringField("name", it.id)
      gen?.writeNumberField("health", it.health)
      gen?.writeStringField("direction", if(it.isFacingRight) "RIGHT" else "LEFT")

      gen?.writeArrayFieldStart("location")
      it.location.forEach {
        gen?.writeStartObject()
        gen?.writeNumberField("x", it.x)
        gen?.writeNumberField("y", it.y)
        gen?.writeEndObject()
      }
      gen?.writeEndArray()

      gen?.writeArrayFieldStart("attacking_cells")
      // TODO determine attacked cells
      gen?.writeEndArray()

      gen?.writeArrayFieldStart("blocking_cells")
      // TODO determine blocked cells
      gen?.writeEndArray()

      gen?.writeEndObject()
    }
    gen?.writeEndArray()
    gen?.writeNumberField("time_remaining", value.timeRemaining())
    gen?.writeStringField("winner", value.getWinner())
    gen?.writeEndObject()
  }
}