package org.example.domain

enum class PrizeCategory(val prize: Int) {
    FIRST(100_000),
    SECOND(50_000),
    THIRD(100),
    FOURTH(5),
    NONE(0),
    ;

    companion object {
        fun getRank(
            matchCount: Int,
            hasBonus: Boolean,
        ) = when {
            matchCount == 6 -> FIRST
            matchCount == 5 && hasBonus -> SECOND
            matchCount == 5 -> THIRD
            matchCount == 4 -> FOURTH
            else -> NONE
        }

        fun getTotalEarning(
            purchaseMoney: Int,
            totalResult: LottoResult,
        ): Double {
            val totalEarning =
                earningMoney(totalResult)
            return totalEarning.toDouble() / purchaseMoney
        }

        fun earningMoney(totalResult: LottoResult): Int = totalResult.winningPrice.entries.sumOf { it.key.prize * it.value }
    }
}
