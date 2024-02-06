package org.example.domain.check

import org.example.config.LottoConfig

@JvmInline
value class BonusLottoNumbersCheck(private val numbers: List<Int>) {
    init {
        require(numbers.distinct().size == 7) { LottoConfig.ERROR_MESSAGE_DUPLICATE }
        require(numbers.all { it in (LottoConfig.START_NUMBER..LottoConfig.END_NUMBER) }) { LottoConfig.ERROR_MESSAGE_NUMBER_RANGE }
    }
}
