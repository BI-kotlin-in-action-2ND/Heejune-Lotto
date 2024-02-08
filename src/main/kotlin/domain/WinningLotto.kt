package org.example.domain

import org.example.domain.check.BonusLottoNumbers

class WinningLotto(
    numbers: List<Int>,
    val bonusNumber: Int,
) : LottoTicket(numbers.sorted()) {
    init {
        BonusLottoNumbers(numbers + bonusNumber)
    }
}
