package org.example.domain.generator

import org.example.constant.LottoConstant
import org.example.domain.LottoTicket
import org.example.domain.WinningLotto
import org.example.domain.ticket.LottoNumber

class AutoLottoGenerator() {
    private val numberRange = LottoConstant.START_NUMBER..LottoConstant.END_NUMBER
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
