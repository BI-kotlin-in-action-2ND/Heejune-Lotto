package org.example.domain.generator

import org.example.constant.LottoConstant
import org.example.domain.LottoTicket
import org.example.domain.WinningLotto

class AutoLottoGenerator() : LottoGenerator {
    private val numberRange = LottoConstant.START_NUMBER..LottoConstant.END_NUMBER
    private val numberCount = LottoConstant.LOTTO_TICKET_SIZE

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
