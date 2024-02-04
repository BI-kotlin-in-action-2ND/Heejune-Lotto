package org.example.domain

class BonusLotto(
    numbers: List<Int>,
    val bonusNumber: Int,
) : LottoTicket(numbers) {
    init {
        require(bonusNumber !in numbers) { "보너스 번호는 로또 번호와 중복되지 않아야 합니다" }
        require(bonusNumber in 1..45) { "보너스 번호는 1부터 45 사이의 숫자여야 합니다" }
    }
}
