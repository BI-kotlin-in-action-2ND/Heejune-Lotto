package org.example.domain

class LottoTickets(private val tickets: List<LottoTicket>) {
    val size: Int
        get() = tickets.size

    fun add(ticket: LottoTicket): LottoTickets {
        return LottoTickets(tickets + ticket)
    }

    fun get(index: Int): LottoTicket {
        return tickets[index]
    }

    fun matchAll(winningNumbers: List<Int>): List<Int> {
        return tickets.map { it.match(winningNumbers) }
    }

    fun containsBonus(bonusNumber: Int): List<Boolean> {
        return tickets.map { it.hasBonus(bonusNumber) }
    }

    fun formattedTickets(): List<String> {
        return tickets.map { it.formattedNumbers(", ", "[", "]") }
    }

    operator fun plus(other: LottoTickets): LottoTickets {
        return LottoTickets(tickets + other.tickets)
    }
}
