package org.example.domain.generator

import org.example.domain.LottoTicket
import org.example.domain.WinningLotto

class AutoLottoGenerator(
    private val numberRange: IntRange = 1..45,
    private val numberCount: Int = 6,
) : LottoGenerator {
    override fun generate(): LottoTicket {
        val numbers = numberRange.shuffled().take(numberCount).sorted()
        return LottoTicket(numbers)
    }

    fun generateWinningNumbers(): WinningLotto {
        val numbers = numberRange.shuffled().take(numberCount).sorted()
        // 보너스 번호는 당첨 번호와 중복되지 않는 숫자로 생성
        val bonusNumber = (numberRange - numbers.toSet()).shuffled().first()
        return WinningLotto(numbers, bonusNumber)
    }
}
