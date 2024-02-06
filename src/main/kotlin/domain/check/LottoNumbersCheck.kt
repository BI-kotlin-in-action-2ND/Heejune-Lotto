package org.example.domain.check

import org.example.config.LottoConfig

@JvmInline
value class LottoNumbersCheck(private val numbers: List<Int>) {
    init {
        require(numbers.size == LottoConfig.LOTTO_TICKET_SIZE) { LottoConfig.ERROR_MESSAGE_LOTTO_SIZE }
        require(numbers.distinct().size == LottoConfig.LOTTO_TICKET_SIZE) { LottoConfig.ERROR_MESSAGE_DUPLICATE }
        require(numbers.all { it in (LottoConfig.START_NUMBER..LottoConfig.END_NUMBER) }) { LottoConfig.ERROR_MESSAGE_NUMBER_RANGE }
    }
}
