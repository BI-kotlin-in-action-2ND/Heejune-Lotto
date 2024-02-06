package org.example.domain

open class LottoTicket(val numbers: List<Int>) {
    init {
        LottoNumbersCheck(numbers)
    }

    fun match(winningNumbers: List<Int>): Int {
        return numbers.intersect(winningNumbers.toSet()).size
    }

    fun hasBonus(target: Int): Boolean {
        return target in numbers
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
