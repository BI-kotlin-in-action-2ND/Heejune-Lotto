package org.example.domain

import org.example.domain.ticket.LottoNumber
import org.example.domain.ticket.LottoNumbers

open class LottoTicket(val numbers: Set<LottoNumber>) {
    init {
        LottoNumbers(numbers)
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
