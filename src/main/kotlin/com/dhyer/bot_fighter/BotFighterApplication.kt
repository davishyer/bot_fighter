package com.dhyer.bot_fighter

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BotFighterApplication

fun main(args: Array<String>) {
	SpringApplication.run(BotFighterApplication::class.java, *args)
}
