package org.example.domain.generator

import org.example.domain.LottoTicket
import org.example.domain.LottoTickets
import java.util.TreeSet

open class ManualLottoGenerator {
    fun generate(manualNumbers: TreeSet<Int>): LottoTicket {
        return LottoTicket(manualNumbers)
    }

    fun generateLottoTickets(manualNumbers: List<TreeSet<Int>>): LottoTickets {
        val lottoTickets = manualNumbers.map { generate(it) }.toList()
        return LottoTickets(lottoTickets)
    }
}
