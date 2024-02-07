package org.example.domain

class LottoResult(
    val winningPrice: Map<PrizeCategory, Int>,
) {
    fun calculateTotalEarningPercentage(purchaseAmount: Int): Double = PrizeCategory.getTotalEarning(purchaseAmount, this)

    fun earningMoney(): Int = PrizeCategory.earningMoney(this)
}
