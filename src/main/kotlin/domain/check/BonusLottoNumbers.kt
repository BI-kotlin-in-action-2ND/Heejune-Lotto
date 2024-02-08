package org.example.domain.check

import org.example.constant.LottoConstant

@JvmInline
value class BonusLottoNumbers(private val numbers: Set<Int>) {
    init {
        require(numbers.distinct().size == 7) { LottoConstant.ERROR_MESSAGE_LOTTO_INPUT }
        numbers.map { LottoNumber(it) }
    }
}
