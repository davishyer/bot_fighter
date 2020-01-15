package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.annotations.Private
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/")
class RedirectController {
  @GetMapping
  @Private
  fun index() = RedirectView("/swagger-ui.html")
}
