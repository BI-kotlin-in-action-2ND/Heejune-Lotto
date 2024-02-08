package org.example.domain

import org.example.domain.check.BonusLottoNumbers

class WinningLotto(
    numbers: Set<Int>,
    val bonusNumber: Int,
) : LottoTicket(numbers) {
    init {
        BonusLottoNumbers(numbers + bonusNumber)
    }
}
