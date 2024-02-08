package org.example.domain

enum class PrizeCategory(val prize: Int, val matchCount: Int, val hasBonus: Boolean) {
    FIRST(100_000, 6, false),
    SECOND(50_000, 5, true),
    THIRD(100, 5, false),
    FOURTH(5, 4, false),
    NONE(0, 0, false),
    ;

    companion object {
        fun getRank(
            matchCount: Int,
            hasBonus: Boolean,
        ) = entries.find { it.matchCount == matchCount && it.hasBonus == hasBonus } ?: NONE
    }
}
