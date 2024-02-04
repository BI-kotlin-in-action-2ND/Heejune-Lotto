package org.example.domain

enum class PrizeCategory(val prize: Int) {
    FIRST(100_000),
    SECOND(50_000),
    THIRD(100),
    FOURTH(5),
    NONE(0),
}
