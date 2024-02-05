package org.example.domain.generator

import org.example.domain.LottoTicket

open class ManualLottoGenerator : LottoGenerator {
    fun generate(numbers: List<Int>): LottoTicket = LottoTicket(numbers.sorted())

    override fun generate(): LottoTicket {
        throw UnsupportedOperationException("수동 로또 생성기는 수동으로 번호를 입력해야 합니다")
    }
}
