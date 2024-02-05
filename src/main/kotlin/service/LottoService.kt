package org.example.service

import org.example.config.AppConfig
import org.example.domain.LottoTicket
import org.example.domain.PrizeCategory
import org.example.domain.WinningLotto
import org.example.domain.generator.AutoLottoGenerator
import org.example.domain.generator.ManualLottoGenerator

class LottoService(
    private val autoLottoGenerator: AutoLottoGenerator,
    private val manualLottoGenerator: ManualLottoGenerator,
) {
    constructor(config: AppConfig) : this(
        config.autoLottoGenerator,
        config.manualLottoGenerator,
    )

    fun manualLottoGenerator(manualNumbers: List<Int>): LottoTicket {
        return manualLottoGenerator.generate(manualNumbers)
    }

    fun autoLottoGenerator(purchaseMoney: Int): List<LottoTicket> {
        return (1..purchaseMoney).map { autoLottoGenerator.generate() }
    }

    fun autoWinningLottoGenerator(): WinningLotto {
        return autoLottoGenerator.generateWinningNumbers()
    }

    fun calculateTotalEarningPercentage(
        purchaseMoney: Int,
        totalResult: Map<PrizeCategory, Int>,
    ): Double {
        val totalEarning =
            earningMoney(totalResult)
        return totalEarning.toDouble() / purchaseMoney
    }

    fun earningMoney(totalResult: Map<PrizeCategory, Int>): Int {
        return totalResult.map { (category, count) ->
            category.prize * count
        }.sum()
    }
}
