package org.example.domain

import org.example.domain.ticket.LottoTicket
import org.example.domain.ticket.WinningLotto

class LottoTickets(private val tickets: List<LottoTicket>) {
    val size: Int
        get() = tickets.size

    fun get(index: Int): LottoTicket {
        return tickets[index]
    }

    fun matchAll(winningNumbers: WinningLotto): List<Int> {
        return tickets.map { it.match(winningNumbers) }
    }

    fun containsBonus(bonusNumber: WinningLotto): List<Boolean> {
        return tickets.map { it.hasBonus(bonusNumber.bonusNumber) }
    }

    fun formattedTickets(): List<String> {
        return tickets.map { it.formattedNumbers(", ", "[", "]") }
    }

    operator fun plus(other: LottoTickets): LottoTickets {
        return LottoTickets(tickets + other.tickets)
    }
}
