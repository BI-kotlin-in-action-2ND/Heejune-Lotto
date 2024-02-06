package org.example.domain.generator

import org.example.domain.LottoTicket

open class ManualLottoGenerator : LottoGenerator {
    fun generate(manualNumbers: List<List<Int>>): List<LottoTicket> {
        return manualNumbers.map { LottoTicket(it.sorted()) }
    }

    override fun generate(): LottoTicket {
        throw UnsupportedOperationException("수동 로또 생성기는 수동으로 번호를 입력해야 합니다")
    }
}
