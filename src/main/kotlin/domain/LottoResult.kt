package org.example.domain

import org.example.constant.LottoConstant.PERCENTAGE

class LottoResult(
    val winningPrice: Map<PrizeCategory, Int>,
) {
    fun calculateTotalEarningPercentage(purchaseAmount: Int): Double {
        val totalEarning = earningMoney()
        return totalEarning.toDouble() / purchaseAmount * PERCENTAGE
    }

    fun earningMoney(): Int = winningPrice.entries.sumOf { it.key.prize * it.value }
}
