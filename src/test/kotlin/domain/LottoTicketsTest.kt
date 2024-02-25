package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.example.constant.LottoConstant.LOTTO_TICKET_SIZE
import org.example.constant.LottoConstant.LottoMinNumber
import org.example.domain.LottoTickets
import org.example.domain.ticket.LottoNumber
import org.example.domain.ticket.LottoTicket
import org.example.domain.ticket.WinningLotto

class LottoTicketsTest : FunSpec({

    context("LottoTickets 유효성 검증") {
        val ticket1Numbers = (LottoMinNumber until LottoMinNumber + LOTTO_TICKET_SIZE).toList()
        val ticket2Numbers = (LottoMinNumber + 7 until LottoMinNumber + LOTTO_TICKET_SIZE + 7).toList()
        val ticket1 = LottoTicket(ticket1Numbers.map { LottoNumber(it) }.toSet())
        val ticket2 = LottoTicket(ticket2Numbers.map { LottoNumber(it) }.toSet())
        val tickets = LottoTickets(listOf(ticket1, ticket2))

        test("size는 올바른 티켓 수를 반환해야 합니다") {
            tickets.size shouldBe 2
        }

        test("get은 올바른 LottoTicket을 검색해야 합니다") {
            tickets.get(0) shouldBe ticket1
            tickets.get(1) shouldBe ticket2
        }

        test("matchAll은 WinningLotto에 대해 번호들이 올바르게 일치하는지 확인해야 합니다") {
            val winningNumbers =
                WinningLotto(
                    ticket1Numbers.take(LOTTO_TICKET_SIZE).map { LottoNumber(it) }.toSet(),
                    LottoMinNumber + LOTTO_TICKET_SIZE,
                )
            tickets.matchAll(winningNumbers) shouldContainExactly listOf(LOTTO_TICKET_SIZE, 0)
        }

        test("containsBonus는 WinningLotto에 대해 보너스 번호가 올바르게 일치하는지 확인해야 합니다") {
            val winningLotto = WinningLotto(ticket1Numbers.map { LottoNumber(it) }.toSet(), 10)
            tickets.containsBonus(winningLotto) shouldContainExactly listOf(false, true)
        }

        test("formattedTickets는 LottoTicket들을 올바르게 포맷팅해야 합니다") {
            tickets.formattedTickets() shouldContainExactly
                listOf(
                    ticket1Numbers.map { it.toString().padStart(2, '0') }.joinToString(", ", "[", "]"),
                    ticket2Numbers.map { it.toString().padStart(2, '0') }.joinToString(", ", "[", "]"),
                )
        }

        test("plus는 LottoTickets를 올바르게 결합해야 합니다") {
            val additionalTicketNumbers = (LottoMinNumber + LOTTO_TICKET_SIZE until LottoMinNumber + LOTTO_TICKET_SIZE + 6).toList()
            val ticket3 = LottoTicket(additionalTicketNumbers.map { LottoNumber(it) }.toSet())
            val newTickets = LottoTickets(listOf(ticket3))

            val combinedTickets = tickets + newTickets
            combinedTickets.size shouldBe 3
        }
    }
})
