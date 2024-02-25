package org.example.domain.ticket

import org.example.constant.LottoConstant
import org.example.constant.LottoConstant.ERROR_MESSAGE_NUMBER_RANGE

@JvmInline
value class LottoNumber(
    val number: Int,
) : Comparable<LottoNumber> {
    init {
        require(number in (LottoConstant.START_NUMBER..LottoConstant.END_NUMBER)) { ERROR_MESSAGE_NUMBER_RANGE }
    }

    override fun compareTo(other: LottoNumber): Int {
        return number.compareTo(other.number)
    }
}
