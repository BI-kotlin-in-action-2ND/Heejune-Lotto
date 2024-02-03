package org.example.domain

class LottoTicket(private val numbers: List<Int>) {
    fun validate(): Boolean {
        if (numbers.size != 6) {
            return false
        }

        if (numbers.toSet().size != 6) {
            return false
        }

        if (numbers.any { it !in 1..45 }) {
            return false
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
