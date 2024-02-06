package org.example.domain.generator

import org.example.config.LottoConfig
import org.example.domain.LottoTicket

open class ManualLottoGenerator : LottoGenerator {
    fun generate(manualNumbers: List<List<Int>>): List<LottoTicket> {
        return manualNumbers.map { LottoTicket(it.sorted()) }
    }

    override fun generate(): LottoTicket {
        throw UnsupportedOperationException(LottoConfig.MANUAL_GENERATOR_NOT_SUPPORTED)
    }
}
