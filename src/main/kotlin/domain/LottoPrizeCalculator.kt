package org.example.domain

import org.example.domain.ticket.WinningLotto

class LottoPrizeCalculator(
    private val userLottoTickets: LottoTickets,
    private val winningLotto: WinningLotto,
) {
    fun calculateResults(): LottoResult {
        val matchCounts = userLottoTickets.matchAll(winningLotto)
        val hasBonuses = userLottoTickets.containsBonus(winningLotto)
        val results = matchCounts.zip(hasBonuses)
        return LottoCheckRank(results).checkRank()
    }
}
