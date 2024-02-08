package org.example.domain.generator

import org.example.domain.LottoTicket

open class ManualLottoGenerator {
    fun generate(manualNumbers: Set<Int>): LottoTicket {
        return LottoTicket(manualNumbers)
    }
}
