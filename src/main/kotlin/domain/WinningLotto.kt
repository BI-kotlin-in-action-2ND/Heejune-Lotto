package org.example.domain

import org.example.domain.check.BonusLottoNumbers
import java.util.TreeSet

class WinningLotto(
    numbers: TreeSet<Int>,
    val bonusNumber: Int,
) : LottoTicket(numbers) {
    init {
        BonusLottoNumbers(numbers + bonusNumber)
    }
}
