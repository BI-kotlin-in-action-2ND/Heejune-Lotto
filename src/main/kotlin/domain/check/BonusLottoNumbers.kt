package org.example.domain.check

import org.example.constant.LottoConstant

@JvmInline
value class BonusLottoNumbers(private val numbers: List<Int>) {
    init {
        require(numbers.distinct().size == 7) { LottoConstant.ERROR_MESSAGE_DUPLICATE }
        numbers.map { LottoNumber(it) }
    }
}
