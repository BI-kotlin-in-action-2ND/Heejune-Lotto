package org.example.domain.check

import org.example.constant.LottoConstant

@JvmInline
value class LottoNumbers(private val numbers: Set<Int>) {
    init {
        require(numbers.size == LottoConstant.LOTTO_TICKET_SIZE) { LottoConstant.ERROR_MESSAGE_LOTTO_INPUT }
        require(numbers.distinct().size == LottoConstant.LOTTO_TICKET_SIZE) { LottoConstant.ERROR_MESSAGE_LOTTO_INPUT }
        numbers.map { LottoNumber(it) }
    }
}
