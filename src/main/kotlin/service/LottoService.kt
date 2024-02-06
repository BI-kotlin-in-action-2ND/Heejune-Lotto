package org.example.service

import org.example.domain.LottoTickets
import org.example.domain.PrizeCategory
import org.example.domain.WinningLotto
import org.example.domain.generator.AutoLottoGenerator
import org.example.domain.generator.ManualLottoGenerator

class LottoService(
    private val autoLottoGenerator: AutoLottoGenerator,
    private val manualLottoGenerator: ManualLottoGenerator,
) {
    fun manualLottoGenerator(manualNumbers: List<List<Int>>): LottoTickets {
        return manualLottoGenerator.generate(manualNumbers)
    }

    fun autoLottoGenerator(purchaseMoney: Int): LottoTickets {
        val lottoTickets = (1..purchaseMoney).map { autoLottoGenerator.generate() }.toList()
        return LottoTickets(lottoTickets)
    }

    fun autoWinningLottoGenerator(): WinningLotto {
        return autoLottoGenerator.generateWinningNumbers()
    }

    fun calculateTotalEarningPercentage(
        purchaseMoney: Int,
        totalResult: Map<PrizeCategory, Int>,
    ): Double = PrizeCategory.getTotalEarning(purchaseMoney, totalResult)

    fun earningMoney(totalResult: Map<PrizeCategory, Int>): Int = PrizeCategory.earningMoney(totalResult)
}
