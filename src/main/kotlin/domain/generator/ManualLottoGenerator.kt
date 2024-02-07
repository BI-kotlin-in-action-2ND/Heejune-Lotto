package org.example.domain.generator

import org.example.constant.LottoConstant
import org.example.domain.LottoTicket
import org.example.domain.LottoTickets

open class ManualLottoGenerator : LottoGenerator {
    fun generate(manualNumbers: List<List<Int>>): LottoTickets {
        return LottoTickets(
            manualNumbers.map { LottoTicket(it) },
        )
    }

    override fun generate(): LottoTicket {
        throw UnsupportedOperationException(LottoConstant.MANUAL_GENERATOR_NOT_SUPPORTED)
    }
}
