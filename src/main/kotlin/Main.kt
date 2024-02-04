package org.example

import org.example.config.AppConfig
import org.example.controller.GameController

fun main() {
    val config = AppConfig()
    GameController(config).start()
}
