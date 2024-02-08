package org.example.domain.generator

import org.example.constant.LottoConstant
import org.example.domain.LottoTicket

open class ManualLottoGenerator : LottoGenerator {
    fun generate(manualNumbers: List<Int>): LottoTicket {
        return LottoTicket(manualNumbers.sorted())
    }

    override fun generate(): LottoTicket {
        throw UnsupportedOperationException(LottoConstant.MANUAL_GENERATOR_NOT_SUPPORTED)
    }
}
