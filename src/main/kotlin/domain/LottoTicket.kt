package org.example.domain

import org.example.domain.check.LottoNumbers
import java.util.TreeSet

open class LottoTicket(val numbers: TreeSet<Int>) {
    init {
        LottoNumbers(numbers)
    }

    fun match(winningNumbers: LottoTicket): Int {
        return numbers.intersect(winningNumbers.numbers).size
    }

    fun hasBonus(target: Int): Boolean {
//        return numbers.binarySearch(target) >= 0
        return numbers.contains(target)
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
