package com.dhyer.bot_fighter

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health-check")
class HealthCheckController {
  @GetMapping
  fun index() {}
}
