package org.example.domain

class WinningLotto(
    numbers: List<Int>,
    val bonusNumber: Int,
) : LottoTicket(numbers) {
    init {
        BonusLottoNumbersCheck(numbers + bonusNumber)
    }
}
