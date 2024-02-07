package org.example.domain.check

import org.example.constant.LottoConstant

@JvmInline
value class BonusLottoNumbersCheck(private val numbers: List<Int>) {
    init {
        require(numbers.distinct().size == 7) { LottoConstant.ERROR_MESSAGE_DUPLICATE }
        require(numbers.all { it in (LottoConstant.START_NUMBER..LottoConstant.END_NUMBER) }) { LottoConstant.ERROR_MESSAGE_NUMBER_RANGE }
    }
}
