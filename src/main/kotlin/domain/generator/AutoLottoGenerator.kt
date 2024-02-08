package org.example.domain.generator

import org.example.constant.LottoConstant
import org.example.domain.LottoTicket
import org.example.domain.WinningLotto
import java.util.TreeSet

class AutoLottoGenerator() {
    private val numberRange = LottoConstant.START_NUMBER..LottoConstant.END_NUMBER
    private val numberCount = LottoConstant.LOTTO_TICKET_SIZE

    fun generate(): LottoTicket {
        val numbers = TreeSet(numberRange.shuffled().take(numberCount))
        return LottoTicket(numbers)
    }

    fun generateWinningNumbers(): WinningLotto {
        val numbers = TreeSet(numberRange.shuffled().take(numberCount))
        val bonusNumber = (numberRange - numbers).random()
        return WinningLotto(numbers, bonusNumber)
    }
}
