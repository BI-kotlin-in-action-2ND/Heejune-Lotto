package org.example.domain

class BonusLotto(
    numbers: List<Int>,
    val bonusNumber: Int,
) : LottoTicket(numbers) {
    override fun validate(): Boolean {
        super.validate()

        if (numbers.contains(bonusNumber)) {
            throw IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다")
        }

        if (bonusNumber !in 1..45) {
            throw IllegalArgumentException("보너스 번호는 1부터 45 사이의 숫자여야 합니다")
        }

        return true
    }
}
