package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.maps.shouldContainExactly
import org.example.domain.LottoPrizeCalculator
import org.example.domain.LottoTickets
import org.example.domain.PrizeCategory
import org.example.domain.ticket.LottoNumber
import org.example.domain.ticket.LottoTicket
import org.example.domain.ticket.WinningLotto

class LottoPrizeCalculatorTest : FunSpec({

    context("LottoPrizeCalculator Result Calculation") {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val winningNumbers = numbers.map { LottoNumber(it) }.toSet()
        val bonusNumber = 7
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)

        test("No matches result in no prizes") {
            val userNumbers = listOf(10, 20, 30, 40, 41, 42).map { LottoNumber(it) }.toSet()
            val userTickets = LottoTickets(listOf(LottoTicket(userNumbers)))
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
            val userNumbers = listOf(1, 2, 3, 4, 5, bonusNumber).map { LottoNumber(it) }.toSet()
            val userTickets = LottoTickets(listOf(LottoTicket(userNumbers)))
            val calculator = LottoPrizeCalculator(userTickets, winningLotto)

            val results = calculator.calculateResults()

            results.winningPrice.shouldContainExactly(mapOf(PrizeCategory.SECOND to 1))
        }

        test("Mixed ticket results yield correct prize distribution") {
            val userTickets =
                LottoTickets(
                    listOf(
                        LottoTicket(winningNumbers),
                        LottoTicket(listOf(1, 2, 3, 4, 5, bonusNumber).map { LottoNumber(it) }.toSet()),
                        LottoTicket(listOf(1, 2, 3, 4, 5, 10).map { LottoNumber(it) }.toSet()),
                        LottoTicket(listOf(1, 2, 3, 4, 10, 20).map { LottoNumber(it) }.toSet()),
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
