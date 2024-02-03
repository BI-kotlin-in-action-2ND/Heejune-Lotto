package org.example.domain

open class LottoTicket(val numbers: List<Int>) {
    open fun validate(): Boolean {
        if (numbers.size != 6) {
            throw IllegalArgumentException("로또 번호는 6개여야 합니다")
        }

        if (numbers.toSet().size != 6) {
            throw IllegalArgumentException("로또 번호는 중복될 수 없습니다")
        }

        if (numbers.any { it !in 1..45 }) {
            throw IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다")
        }

        return true
    }

    fun match(target: List<Int>): Int {
        return numbers.count { it in target }
    }

    fun hasBonus(target: Int): Boolean {
        return numbers.contains(target)
    }

    fun sortedNumbers(): List<Int> {
        return numbers.sorted()
    }

    fun formattedNumbers(): String {
        return numbers.joinToString(", ") {
            it.toString().padStart(2, '0')
        }
    }
}
