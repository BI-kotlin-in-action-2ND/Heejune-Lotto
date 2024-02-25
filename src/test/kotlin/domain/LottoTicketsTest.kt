package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.example.domain.LottoTickets
import org.example.domain.ticket.LottoNumber
import org.example.domain.ticket.LottoTicket
import org.example.domain.ticket.WinningLotto

class LottoTicketsTest : FunSpec({

    context("LottoTickets 유효성 검증") {
        val ticket1 = LottoTicket(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet())
        val ticket2 = LottoTicket(listOf(7, 8, 9, 10, 11, 12).map { LottoNumber(it) }.toSet())
        val tickets = LottoTickets(listOf(ticket1, ticket2))

        test("size는 올바른 티켓 수를 반환해야 합니다") {
            tickets.size shouldBe 2
        }

        test("get은 올바른 LottoTicket을 검색해야 합니다") {
            tickets.get(0) shouldBe ticket1
            tickets.get(1) shouldBe ticket2
        }

        test("matchAll은 WinningLotto에 대해 번호들이 올바르게 일치하는지 확인해야 합니다") {
            val userNumbers = listOf(1, 2, 3, 7, 8, 9).map { LottoNumber(it) }.toSet()
            val winningNumbers = WinningLotto(userNumbers, 10)
            tickets.matchAll(winningNumbers) shouldContainExactly listOf(3, 3)
        }

        test("containsBonus는 WinningLotto에 대해 보너스 번호가 올바르게 일치하는지 확인해야 합니다") {
            val userNumbers = listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()
            val winningLotto = WinningLotto(userNumbers, 10)
            tickets.containsBonus(winningLotto) shouldContainExactly listOf(false, true)
        }

        test("formattedTickets는 LottoTicket들을 올바르게 포맷팅해야 합니다") {
            tickets.formattedTickets() shouldContainExactly
                listOf(
                    "[01, 02, 03, 04, 05, 06]",
                    "[07, 08, 09, 10, 11, 12]",
                )
        }

        test("plus는 LottoTickets를 올바르게 결합해야 합니다") {
            val lottoNumbers = listOf(13, 14, 15, 16, 17, 18).map { LottoNumber(it) }.toSet()
            val ticket3 = LottoTicket(lottoNumbers)
            val newTickets = LottoTickets(listOf(ticket3))

            val combinedTickets = tickets + newTickets
            combinedTickets.size shouldBe 3
        }
    }
})
