package org.example.domain.generator

import org.example.domain.LottoTicket

open class ManualLottoGenerator(
    val numbers: List<Int>,
) : LottoGenerator {
    override fun generate(): LottoTicket = LottoTicket(numbers)
}
