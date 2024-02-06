package org.example.domain.generator

import org.example.config.LottoConfig
import org.example.domain.LottoTicket
import org.example.domain.LottoTickets

open class ManualLottoGenerator : LottoGenerator {
    fun generate(manualNumbers: List<List<Int>>): LottoTickets {
        return LottoTickets(
            manualNumbers.map { LottoTicket(it) },
        )
    }

    override fun generate(): LottoTicket {
        throw UnsupportedOperationException(LottoConfig.MANUAL_GENERATOR_NOT_SUPPORTED)
    }
}
