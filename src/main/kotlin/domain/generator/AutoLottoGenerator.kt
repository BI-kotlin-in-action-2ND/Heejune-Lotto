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
        val bonusNumber = (numberRange - numbers.toSet()).shuffled().first()
        return WinningLotto(numbers, bonusNumber)
    }
}
