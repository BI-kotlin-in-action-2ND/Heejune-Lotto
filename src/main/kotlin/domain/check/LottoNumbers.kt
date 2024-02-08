package org.example.domain.check

import org.example.constant.LottoConstant

@JvmInline
value class LottoNumbers(private val numbers: List<Int>) {
    init {
        require(numbers.size == LottoConstant.LOTTO_TICKET_SIZE) { LottoConstant.ERROR_MESSAGE_LOTTO_SIZE }
        require(numbers.distinct().size == LottoConstant.LOTTO_TICKET_SIZE) { LottoConstant.ERROR_MESSAGE_DUPLICATE }
        numbers.map { LottoNumber(it) }
    }
}
