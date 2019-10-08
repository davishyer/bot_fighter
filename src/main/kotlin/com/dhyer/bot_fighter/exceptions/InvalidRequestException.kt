package com.dhyer.bot_fighter.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidRequestException : RuntimeException {
  constructor(message: String, ex: Exception?): super(message, ex) {}
  constructor(message: String): super(message) {}
  constructor(ex: Exception): super(ex) {}
}