package org.example.service

import org.example.domain.LottoResult
import org.example.domain.LottoTickets
import org.example.domain.WinningLotto
import org.example.domain.generator.AutoLottoGenerator
import org.example.domain.generator.ManualLottoGenerator


class LottoService(
    private val autoLottoGenerator: AutoLottoGenerator,
    private val manualLottoGenerator: ManualLottoGenerator,
) {
    fun manualLottoGenerator(manualNumbers: List<Set<Int>>): LottoTickets {
        return manualLottoGenerator.generateLottoTickets(manualNumbers)
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
        totalResult: LottoResult,
    ): Double = totalResult.calculateTotalEarningPercentage(purchaseMoney)

    fun earningMoney(totalResult: LottoResult): Int = totalResult.earningMoney()
}
