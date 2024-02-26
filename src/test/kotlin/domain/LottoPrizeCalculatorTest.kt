package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.maps.shouldContainExactly
import org.example.constant.LottoConstant.LOTTO_TICKET_SIZE
import org.example.constant.LottoConstant.LottoMinNumber
import org.example.domain.LottoPrizeCalculator
import org.example.domain.LottoTickets
import org.example.domain.PrizeCategory
import org.example.domain.ticket.LottoNumber
import org.example.domain.ticket.LottoTicket
import org.example.domain.ticket.WinningLotto

class LottoPrizeCalculatorTest : FunSpec({

    context("로또 당첨금 계산기 테스트") {
        val numbers = (LottoMinNumber until LottoMinNumber + LOTTO_TICKET_SIZE).toList()
        val winningNumbers = numbers.map { LottoNumber(it) }.toSet()
        val bonusNumber = LottoMinNumber + LOTTO_TICKET_SIZE
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)

        test("당첨되지 않은 티켓은 당첨금이 없어야 한다.") {
            val userNumbers =
                (LottoMinNumber + LOTTO_TICKET_SIZE + 1 until LottoMinNumber + LOTTO_TICKET_SIZE + 7).toList().map {
                    LottoNumber(
                        it,
                    )
                }.toSet()
            val userTickets = LottoTickets(listOf(LottoTicket(userNumbers)))
            val calculator = LottoPrizeCalculator(userTickets, winningLotto)

            val results = calculator.calculateResults()

            results.winningPrice.shouldBeEmpty()
        }

        test("모든 번호 일치 시 최고 등급의 당첨금을 받아야 한다.") {
            val userTickets = LottoTickets(listOf(LottoTicket(winningNumbers)))
            val calculator = LottoPrizeCalculator(userTickets, winningLotto)

            val results = calculator.calculateResults()

            results.winningPrice.shouldContainExactly(mapOf(PrizeCategory.FIRST to 1))
        }

        test("주요 번호와 보너스 번호 일치 시 특별 등급의 당첨금을 받아야 한다.") {
            val primaryNumbers = numbers.take(LOTTO_TICKET_SIZE - 1)
            val userNumbers = primaryNumbers.plus(bonusNumber).map { LottoNumber(it) }.toSet()
            val userTickets = LottoTickets(listOf(LottoTicket(userNumbers)))
            val calculator = LottoPrizeCalculator(userTickets, winningLotto)

            val results = calculator.calculateResults()

            results.winningPrice.shouldContainExactly(mapOf(PrizeCategory.SECOND to 1))
        }

        test("다양한 등급의 당첨금 분배를 올바르게 계산해야 한다.") {
            val primaryNumbers = numbers.take(LOTTO_TICKET_SIZE - 1)

            val userTickets =
                LottoTickets(
                    listOf(
                        LottoTicket(winningNumbers),
                        LottoTicket(primaryNumbers.plus(bonusNumber).map { LottoNumber(it) }.toSet()),
                        LottoTicket(
                            primaryNumbers.plus(LottoMinNumber + LOTTO_TICKET_SIZE + 7).map { LottoNumber(it) }.toSet(),
                        ),
                    ),
                )
            val calculator = LottoPrizeCalculator(userTickets, winningLotto)

            val results = calculator.calculateResults()

            results.winningPrice.shouldContainExactly(
                mapOf(
                    PrizeCategory.FIRST to 1,
                    PrizeCategory.SECOND to 1,
                    PrizeCategory.THIRD to 1,
                ),
            )
        }
    }
})
