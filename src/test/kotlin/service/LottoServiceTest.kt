package service

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.example.domain.LottoResult
import org.example.domain.PrizeCategory
import org.example.domain.generator.AutoLottoGenerator
import org.example.domain.generator.ManualLottoGenerator
import org.example.service.LottoService

class LottoServiceTest : StringSpec({

    "generate manual LottoTickets with provided numbers" {
        val manualNumbers = listOf(setOf(1, 2, 3, 4, 5, 6))
        val manualLottoGenerator = ManualLottoGenerator()
        val autoLottoGenerator = AutoLottoGenerator()
        val lottoService = LottoService(autoLottoGenerator, manualLottoGenerator)

        val result = lottoService.manualLottoGenerator(manualNumbers)

        result.size shouldBe 1
        result.get(0).numbers shouldBe manualNumbers.first()
    }

    "generate auto LottoTickets with provided purchase money" {
        val purchaseMoney = 5
        val manualLottoGenerator = ManualLottoGenerator()
        val autoLottoGenerator = AutoLottoGenerator()
        val lottoService = LottoService(autoLottoGenerator, manualLottoGenerator)

        val result = lottoService.autoLottoGenerator(purchaseMoney)

        result.size shouldBe purchaseMoney
    }

    "generate auto WinningLotto" {
        val manualLottoGenerator = ManualLottoGenerator()
        val autoLottoGenerator = AutoLottoGenerator()
        val lottoService = LottoService(autoLottoGenerator, manualLottoGenerator)

        val result = lottoService.autoWinningLottoGenerator()

        result.numbers.size shouldBe 6
    }

    "calculate total earning percentage correctly for known results" {
        val purchaseMoney = 1000
        val winningPrice = mapOf(PrizeCategory.FIRST to 1)
        val totalPrize = PrizeCategory.FIRST.prize
        val lottoResult = LottoResult(winningPrice)

        val manualLottoGenerator = ManualLottoGenerator()
        val autoLottoGenerator = AutoLottoGenerator()
        val lottoService = LottoService(autoLottoGenerator, manualLottoGenerator)

        val result = lottoService.calculateTotalEarningPercentage(purchaseMoney, lottoResult)

        val expectedPercentage = totalPrize.toDouble() / purchaseMoney
        result shouldBe (expectedPercentage plusOrMinus 0.01)
    }

    "calculate total earning money correctly for known results" {
        val winningPrice = mapOf(PrizeCategory.FIRST to 1)
        val totalPrize = PrizeCategory.FIRST.prize
        val lottoResult = LottoResult(winningPrice)
        val lottoService = LottoService(AutoLottoGenerator(), ManualLottoGenerator())

        val result = lottoService.earningMoney(lottoResult)

        result shouldBe totalPrize
    }
})
