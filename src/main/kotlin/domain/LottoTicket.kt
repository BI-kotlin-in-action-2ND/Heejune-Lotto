package org.example.domain

import org.example.domain.check.LottoNumbers

open class LottoTicket(val numbers: List<Int>) {
    init {
        LottoNumbers(numbers)
    }

    fun match(winningNumbers: List<Int>): Int {
        return numbers.intersect(winningNumbers.toSet()).size
    }

    fun hasBonus(target: Int): Boolean {
        return numbers.binarySearch(target) >= 0
    }

    fun formattedNumbers(
        sep: String,
        pre: String,
        post: String,
    ): String {
        return numbers.joinToString(sep, pre, post) {
            it.toString().padStart(2, '0')
        }
    }
}
