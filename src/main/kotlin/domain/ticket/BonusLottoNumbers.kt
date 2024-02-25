package org.example.domain.ticket

import org.example.constant.LottoConstant

class BonusLottoNumbers(private val numbers: Set<LottoNumber>) {
    init {
        require(numbers.distinct().size == LottoConstant.BONUS_TICKET_SIZE) { LottoConstant.ERROR_MESSAGE_LOTTO_INPUT }
    }
}
