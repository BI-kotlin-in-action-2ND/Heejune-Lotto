package org.example.domain.generator

import org.example.constant.LottoConstant
import org.example.domain.ticket.LottoNumber
import org.example.domain.ticket.LottoTicket
import org.example.domain.ticket.WinningLotto

class AutoLottoGenerator() {
    private val numberRange = LottoConstant.LottoMinNumber..LottoConstant.LottoMaxNumber
    private val numberCount = LottoConstant.LOTTO_TICKET_SIZE

    fun generate(): LottoTicket {
        val numbers = numberRange.shuffled().take(numberCount).sorted()
        val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()
        return LottoTicket(lottoNumbers)
    }

    fun generateWinningNumbers(): WinningLotto {
        val numbers = numberRange.shuffled().take(numberCount).sorted().toSet()
        val bonusNumber = (numberRange - numbers).random()

        val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()
        return WinningLotto(lottoNumbers, bonusNumber)
    }
}
