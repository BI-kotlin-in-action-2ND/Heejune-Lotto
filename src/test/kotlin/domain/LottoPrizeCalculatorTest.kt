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

    context("로또 당첨금 계산기 테스트") {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val winningNumbers = numbers.map { LottoNumber(it) }.toSet()
        val bonusNumber = 7
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)

        test("모든 티켓이 당첨되지 않았을 때는 당첨금이 없어야 한다.") {
            val userNumbers = listOf(10, 20, 30, 40, 41, 42).map { LottoNumber(it) }.toSet()
            val userTickets = LottoTickets(listOf(LottoTicket(userNumbers)))
            val calculator = LottoPrizeCalculator(userTickets, winningLotto)

            val results = calculator.calculateResults()

            results.winningPrice.shouldBeEmpty()
        }

        test("6개 숫자 일치 시 1등 당첨금을 받아야 한다.") {
            val userTickets = LottoTickets(listOf(LottoTicket(winningNumbers)))
            val calculator = LottoPrizeCalculator(userTickets, winningLotto)

            val results = calculator.calculateResults()

            results.winningPrice.shouldContainExactly(mapOf(PrizeCategory.FIRST to 1))
        }

        test("5개 숫자 일치 시 2등 당첨금을 받아야 한다.") {
            val userNumbers = listOf(1, 2, 3, 4, 5, bonusNumber).map { LottoNumber(it) }.toSet()
            val userTickets = LottoTickets(listOf(LottoTicket(userNumbers)))
            val calculator = LottoPrizeCalculator(userTickets, winningLotto)

            val results = calculator.calculateResults()

            results.winningPrice.shouldContainExactly(mapOf(PrizeCategory.SECOND to 1))
        }

        test("4개 숫자 일치 시 3등 당첨금을 받아야 한다.") {
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
