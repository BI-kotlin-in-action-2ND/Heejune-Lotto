package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.doubles.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.example.constant.LottoConstant.PERCENTAGE
import org.example.domain.LottoResult
import org.example.domain.PrizeCategory

class LottoResultTest : FunSpec({

    context("LottoResult functionality") {
        val prizeCategory1 = PrizeCategory.FIRST
        val prizeCategory2 = PrizeCategory.SECOND
        val winningPrice =
            mapOf(
                prizeCategory1 to 1,
                prizeCategory2 to 2,
            )
        val lottoResult = LottoResult(winningPrice)

        test("earningMoney should calculate total prize money correctly") {
            val expectedTotalPrizeMoney = prizeCategory1.prize * 1 + prizeCategory2.prize * 2
            lottoResult.earningMoney() shouldBe expectedTotalPrizeMoney
        }

        test("calculateTotalEarningPercentage should calculate the correct percentage of earnings") {
            val purchaseAmount = 10_000

            val expectedEarningsPercentage =
                (prizeCategory1.prize * 1 + prizeCategory2.prize * 2).toDouble() / purchaseAmount * PERCENTAGE
            lottoResult.calculateTotalEarningPercentage(purchaseAmount) shouldBeExactly expectedEarningsPercentage
        }
    }
})
