package org.example

import org.example.config.AppConfig
import org.example.controller.GameController

fun main() {
    val config = AppConfig()
    val gameController = GameController(config)
    var totalEarning = 0
    do {
        totalEarning = gameController.start(totalEarning)
    } while (totalEarning > 0)
}
