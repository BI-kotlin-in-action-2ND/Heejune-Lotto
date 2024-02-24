package org.example.domain.check

import org.example.constant.LottoConstant

class BonusLottoNumbers(private val numbers: Set<Int>) {
    init {
        require(numbers.distinct().size == LottoConstant.BONUS_TICKET_SIZE) { LottoConstant.ERROR_MESSAGE_LOTTO_INPUT }
        numbers.map { LottoNumber(it) }
    }
}
