package org.example.domain.check

import org.example.constant.LottoConstant

@JvmInline
value class LottoNumbersCheck(private val numbers: List<Int>) {
    init {
        require(numbers.size == LottoConstant.LOTTO_TICKET_SIZE) { LottoConstant.ERROR_MESSAGE_LOTTO_SIZE }
        require(numbers.distinct().size == LottoConstant.LOTTO_TICKET_SIZE) { LottoConstant.ERROR_MESSAGE_DUPLICATE }
        require(numbers.all { it in (LottoConstant.START_NUMBER..LottoConstant.END_NUMBER) }) { LottoConstant.ERROR_MESSAGE_NUMBER_RANGE }
    }
}
