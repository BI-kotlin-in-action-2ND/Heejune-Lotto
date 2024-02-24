package org.example.domain.generator

import org.example.domain.LottoTicket
import org.example.domain.LottoTickets


open class ManualLottoGenerator {
    fun generate(manualNumbers: Set<Int>): LottoTicket {
        return LottoTicket(manualNumbers)
    }

    fun generateLottoTickets(manualNumbers: List<Set<Int>>): LottoTickets {
        val lottoTickets = manualNumbers.map { generate(it) }.toList()
        return LottoTickets(lottoTickets)
    }
}
