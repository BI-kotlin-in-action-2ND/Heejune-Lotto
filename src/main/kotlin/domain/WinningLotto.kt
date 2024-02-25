package org.example.domain

import org.example.domain.ticket.BonusLottoNumbers
import org.example.domain.ticket.LottoNumber

class WinningLotto(
    numbers: Set<LottoNumber>,
    val bonusNumber: Int,
) : LottoTicket(numbers) {
    init {
        BonusLottoNumbers(numbers.plus(LottoNumber(bonusNumber)))
    }
}
