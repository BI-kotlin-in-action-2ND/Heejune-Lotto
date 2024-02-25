package org.example.domain.ticket

import org.example.constant.LottoConstant.ERROR_MESSAGE_LOTTO_INPUT
import org.example.constant.LottoConstant.LOTTO_TICKET_SIZE

open class LottoTicket(val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_TICKET_SIZE) { ERROR_MESSAGE_LOTTO_INPUT }
    }

    fun match(winningNumbers: LottoTicket): Int {
        return numbers.intersect(winningNumbers.numbers).size
    }

    fun hasBonus(target: Int): Boolean {
        return numbers.contains(LottoNumber(target))
    }

    fun formattedNumbers(
        sep: String,
        pre: String,
        post: String,
    ): String {
        return numbers.joinToString(sep, pre, post) {
            it.number.toString().padStart(2, '0')
        }
    }
}
