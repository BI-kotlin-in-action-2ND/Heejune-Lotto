package org.example.domain

class LottoGame(
    private val userLottoTickets: List<LottoTicket>,
    private val winningLotto: WinningLotto,
) {
    fun calculateResults(): List<Pair<Int, Boolean>> {
        val result: MutableList<Pair<Int, Boolean>> = mutableListOf()
        userLottoTickets.forEach { lottoTicket ->
            val matchCount = lottoTicket.match(winningLotto.numbers)
            val hasBonus = lottoTicket.hasBonus(winningLotto.bonusNumber)
            result.add(matchCount to hasBonus)
        }
        return result
    }
}
