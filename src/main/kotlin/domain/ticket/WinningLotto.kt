package org.example.domain.ticket

import org.example.constant.LottoConstant.END_NUMBER
import org.example.constant.LottoConstant.ERROR_MESSAGE_DUPLICATE_BONUS
import org.example.constant.LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
import org.example.constant.LottoConstant.START_NUMBER

class WinningLotto(
    numbers: Set<LottoNumber>,
    val bonusNumber: Int,
) : LottoTicket(numbers) {
    init {
        require(numbers.duplicateCheck(bonusNumber)) { ERROR_MESSAGE_DUPLICATE_BONUS }
        require(bonusNumber in (START_NUMBER..END_NUMBER)) { ERROR_MESSAGE_NUMBER_RANGE }
    }
}

fun Set<LottoNumber>.duplicateCheck(bonusNumber: Int): Boolean {
    return !this.any { it.number == bonusNumber }
}
