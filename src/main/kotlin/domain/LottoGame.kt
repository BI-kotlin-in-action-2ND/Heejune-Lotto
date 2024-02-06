package org.example.domain

class LottoGame(
    private val userLottoTickets: LottoTickets,
    private val winningLotto: WinningLotto,
) {
    fun calculateResults(): Map<PrizeCategory, Int> {
        val matchCounts = userLottoTickets.matchAll(winningLotto.numbers)
        val hasBonuses = userLottoTickets.containsBonus(winningLotto.bonusNumber)
        val results = matchCounts.zip(hasBonuses)
        return checkRank(results)
    }

    private fun checkRank(results: List<Pair<Int, Boolean>>): Map<PrizeCategory, Int> {
        return results.groupBy { (matchCount, hasBonus) ->
            PrizeCategory.getRank(matchCount, hasBonus)
        }.mapValues { (_, value) -> value.size }.filterKeys { it != PrizeCategory.NONE }
    }
}
