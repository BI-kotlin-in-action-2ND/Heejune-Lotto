package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.example.domain.LottoTicket
import org.example.domain.LottoTickets
import org.example.domain.WinningLotto
import java.util.TreeSet

class LottoTicketsTest : FunSpec({

    context("LottoTickets operations") {
        val ticket1 = LottoTicket(TreeSet(listOf(1, 2, 3, 4, 5, 6)))
        val ticket2 = LottoTicket(TreeSet(listOf(7, 8, 9, 10, 11, 12)))
        val tickets = LottoTickets(listOf(ticket1, ticket2))

        test("size should return the correct number of tickets") {
            tickets.size shouldBe 2
        }

        test("get should retrieve the correct LottoTicket") {
            tickets.get(0) shouldBe ticket1
            tickets.get(1) shouldBe ticket2
        }

        test("matchAll should correctly match numbers against WinningLotto") {
            val winningNumbers = WinningLotto(TreeSet(listOf(1, 2, 3, 7, 8, 9)), 10)
            tickets.matchAll(winningNumbers) shouldContainExactly listOf(3, 3)
        }

        test("containsBonus should correctly identify if bonus number is present") {
            val winningLotto = WinningLotto(TreeSet(listOf(1, 2, 3, 4, 5, 6)), 10)
            tickets.containsBonus(winningLotto) shouldContainExactly listOf(false, true)
        }

        test("formattedTickets should correctly format tickets") {
            tickets.formattedTickets() shouldContainExactly
                listOf(
                    "[01, 02, 03, 04, 05, 06]",
                    "[07, 08, 09, 10, 11, 12]",
                )
        }

        test("plus operator should combine LottoTickets instances") {
            val ticket3 = LottoTicket(TreeSet(listOf(13, 14, 15, 16, 17, 18)))
            val newTickets = LottoTickets(listOf(ticket3))

            val combinedTickets = tickets + newTickets
            combinedTickets.size shouldBe 3
        }
    }
})
