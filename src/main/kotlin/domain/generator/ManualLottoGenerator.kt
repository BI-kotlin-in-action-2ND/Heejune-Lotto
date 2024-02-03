package org.example.domain.generator

import org.example.domain.LottoTicket

class ManualLottoGenerator(
    private val numbers: List<Int>,
) : LottoGenerator {
    override fun generate(): LottoTicket {
        val lottoTicket = LottoTicket(numbers)

        if (!lottoTicket.validate()) {
            throw IllegalArgumentException("로또 티켓의 유효성 검사 실패")
        }

        return lottoTicket
    }
}
