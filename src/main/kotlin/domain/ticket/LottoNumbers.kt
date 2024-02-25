package org.example.domain.ticket

import org.example.constant.LottoConstant

class LottoNumbers(private val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == LottoConstant.LOTTO_TICKET_SIZE) { LottoConstant.ERROR_MESSAGE_LOTTO_INPUT }
        require(numbers.distinct().size == LottoConstant.LOTTO_TICKET_SIZE) { LottoConstant.ERROR_MESSAGE_LOTTO_INPUT }
    }
}
