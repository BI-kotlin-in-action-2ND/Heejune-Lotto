package org.example.domain

import org.example.domain.check.BonusLottoNumbersCheck

class WinningLotto(
    numbers: List<Int>,
    val bonusNumber: Int,
) : LottoTicket(numbers) {
    init {
        BonusLottoNumbersCheck(numbers + bonusNumber)
    }
}
