package org.example.domain

open class LottoTicket(val numbers: List<Int>) {
    init {
        require(numbers.size == 6) { "로또 번호는 6개여야 합니다" }
        require(numbers.toSet().size == 6) { "로또 번호는 중복되지 않아야 합니다" }
        require(numbers.all { it in 1..45 }) { "로또 번호는 1부터 45 사이여야 합니다" }
    }

    fun match(winningNumbers: List<Int>): Int {
        return numbers.count { it in winningNumbers }
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
