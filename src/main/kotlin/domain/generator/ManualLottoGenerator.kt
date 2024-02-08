package org.example.domain.generator

import org.example.domain.LottoTicket

open class ManualLottoGenerator {
    fun generate(manualNumbers: List<Int>): LottoTicket {
        return LottoTicket(manualNumbers.sorted())
    }
}
