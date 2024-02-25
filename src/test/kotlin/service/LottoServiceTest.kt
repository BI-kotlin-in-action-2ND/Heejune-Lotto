package service

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.example.constant.LottoConstant.PERCENTAGE
import org.example.domain.LottoResult
import org.example.domain.PrizeCategory
import org.example.domain.generator.AutoLottoGenerator
import org.example.domain.generator.ManualLottoGenerator
import org.example.domain.ticket.LottoNumber
import org.example.service.LottoService

class LottoServiceTest : StringSpec({

    "수동 로또 번호 생성 테스트" {
        val manualNumbers =
            listOf(
                setOf(1, 2, 3, 4, 5, 6),
            )
        val manualLottoGenerator = ManualLottoGenerator()
        val autoLottoGenerator = AutoLottoGenerator()
        val lottoService = LottoService(autoLottoGenerator, manualLottoGenerator)

        val result = lottoService.manualLottoGenerator(manualNumbers)

        result.size shouldBe 1
        result.get(0).numbers shouldBe manualNumbers.first().map { LottoNumber(it) }.toSet()
    }

    "구매 금액만큼 자동 로또 생성 테스트" {
        val purchaseMoney = 5
        val manualLottoGenerator = ManualLottoGenerator()
        val autoLottoGenerator = AutoLottoGenerator()
        val lottoService = LottoService(autoLottoGenerator, manualLottoGenerator)

        val result = lottoService.autoLottoGenerator(purchaseMoney)

        result.size shouldBe purchaseMoney
    }

    "당첨 번호 생성 테스트" {
        val manualLottoGenerator = ManualLottoGenerator()
        val autoLottoGenerator = AutoLottoGenerator()
        val lottoService = LottoService(autoLottoGenerator, manualLottoGenerator)

        val result = lottoService.autoWinningLottoGenerator()

        result.numbers.size shouldBe 6
    }

    "결과에 대한 총 수익률 계산 테스트 - 백분율" {
        val purchaseMoney = 1000
        val winningPrice = mapOf(PrizeCategory.FIRST to 1)
        val totalPrize = PrizeCategory.FIRST.prize
        val lottoResult = LottoResult(winningPrice)

        val manualLottoGenerator = ManualLottoGenerator()
        val autoLottoGenerator = AutoLottoGenerator()
        val lottoService = LottoService(autoLottoGenerator, manualLottoGenerator)

        val result = lottoService.calculateTotalEarningPercentage(purchaseMoney, lottoResult)

        val expectedPercentage = totalPrize.toDouble() / purchaseMoney * PERCENTAGE
        result shouldBe (expectedPercentage plusOrMinus 0.01)
    }

    "결과에 대한 총 수익률 계산 테스트 - 총 수익" {
        val winningPrice = mapOf(PrizeCategory.FIRST to 1)
        val totalPrize = PrizeCategory.FIRST.prize
        val lottoResult = LottoResult(winningPrice)
        val lottoService = LottoService(AutoLottoGenerator(), ManualLottoGenerator())

        val result = lottoService.earningMoney(lottoResult)

        result shouldBe totalPrize
    }
})
