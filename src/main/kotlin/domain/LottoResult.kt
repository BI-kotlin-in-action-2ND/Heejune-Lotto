package org.example.domain

import org.example.constant.LottoConstant.ONE_HUNDRED
import org.example.constant.LottoConstant.PERCENTAGE
import kotlin.math.round

class LottoResult(
    val winningPrice: Map<PrizeCategory, Int>,
) {
    fun calculateTotalEarningPercentage(purchaseAmount: Int): Double {
        val totalEarning = earningMoney()
        val result = (totalEarning.toDouble() / purchaseAmount) * PERCENTAGE
        return round(result * ONE_HUNDRED) / ONE_HUNDRED
    }

    fun earningMoney(): Int = winningPrice.entries.sumOf { it.key.prize * it.value }
}
