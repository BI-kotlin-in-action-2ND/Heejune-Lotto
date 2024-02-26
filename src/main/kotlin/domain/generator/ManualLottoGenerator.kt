package org.example.domain.generator

import org.example.domain.LottoTickets
import org.example.domain.ticket.LottoNumber
import org.example.domain.ticket.LottoTicket

open class ManualLottoGenerator {
    fun generate(manualNumbers: Set<Int>): LottoTicket {
        val manualLottoNumbers = manualNumbers.map { LottoNumber(it) }.toSet()
        return LottoTicket(manualLottoNumbers)
    }

    fun generateLottoTickets(manualNumbers: List<Set<Int>>): LottoTickets {
        val lottoTickets = manualNumbers.map { generate(it) }.toList()
        return LottoTickets(lottoTickets)
    }
}
