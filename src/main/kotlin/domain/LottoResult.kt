package org.example.domain

class LottoResult(
    val winningPrice: Map<PrizeCategory, Int>,
) {
    fun calculateTotalEarningPercentage(purchaseAmount: Int): Double {
        val totalEarning = earningMoney()
        return totalEarning.toDouble() / purchaseAmount
    }

    fun earningMoney(): Int = winningPrice.entries.sumOf { it.key.prize * it.value }
}
