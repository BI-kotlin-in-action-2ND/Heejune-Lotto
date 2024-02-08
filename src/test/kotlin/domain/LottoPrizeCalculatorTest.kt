package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.maps.shouldContainExactly
import org.example.domain.LottoPrizeCalculator
import org.example.domain.LottoTicket
import org.example.domain.LottoTickets
import org.example.domain.PrizeCategory
import org.example.domain.WinningLotto
import java.util.TreeSet

class LottoPrizeCalculatorTest : FunSpec({

    context("LottoPrizeCalculator Result Calculation") {
        val winningNumbers = TreeSet(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = 7
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)

        test("No matches result in no prizes") {
            val userTickets = LottoTickets(listOf(LottoTicket(TreeSet(listOf(10, 20, 30, 40, 42, 45)))))
            val calculator = LottoPrizeCalculator(userTickets, winningLotto)

            val results = calculator.calculateResults()

            results.winningPrice.shouldBeEmpty()
        }

        test("Exact match without bonus yields first prize") {
            val userTickets = LottoTickets(listOf(LottoTicket(winningNumbers)))
            val calculator = LottoPrizeCalculator(userTickets, winningLotto)

            val results = calculator.calculateResults()

            results.winningPrice.shouldContainExactly(mapOf(PrizeCategory.FIRST to 1))
        }

        test("5 matches with bonus number yields second prize") {
            val userTickets = LottoTickets(listOf(LottoTicket(TreeSet(listOf(1, 2, 3, 4, 5, bonusNumber)))))
            val calculator = LottoPrizeCalculator(userTickets, winningLotto)

            val results = calculator.calculateResults()

            results.winningPrice.shouldContainExactly(mapOf(PrizeCategory.SECOND to 1))
        }

        test("Mixed ticket results yield correct prize distribution") {
            val userTickets =
                LottoTickets(
                    listOf(
                        LottoTicket(winningNumbers),
                        LottoTicket(TreeSet(listOf(1, 2, 3, 4, 5, bonusNumber))),
                        LottoTicket(TreeSet(listOf(1, 2, 3, 4, 5, 10))),
                        LottoTicket(TreeSet(listOf(1, 2, 3, 4, 15, 16))),
                    ),
                )
            val calculator = LottoPrizeCalculator(userTickets, winningLotto)

            val results = calculator.calculateResults()

            results.winningPrice.shouldContainExactly(
                mapOf(
                    PrizeCategory.FIRST to 1,
                    PrizeCategory.SECOND to 1,
                    PrizeCategory.THIRD to 1,
                    PrizeCategory.FOURTH to 1,
                ),
            )
        }
    }
})
