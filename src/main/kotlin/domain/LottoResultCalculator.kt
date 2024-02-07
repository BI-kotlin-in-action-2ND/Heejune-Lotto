package org.example.domain

class LottoResultCalculator(
    private val userLottoTickets: LottoTickets,
    private val winningLotto: WinningLotto,
) {
    fun calculateResults(): LottoResult {
        val matchCounts = userLottoTickets.matchAll(winningLotto)
        val hasBonuses = userLottoTickets.containsBonus(winningLotto)
        val results = matchCounts.zip(hasBonuses)
        return checkRank(results)
    }

    private fun checkRank(results: List<Pair<Int, Boolean>>): LottoResult {
        val lottoResult =
            results.groupBy { (matchCount, hasBonus) ->
                PrizeCategory.getRank(matchCount, hasBonus)
            }.mapValues { (_, value) -> value.size }.filterKeys { it != PrizeCategory.NONE }
        return LottoResult(lottoResult)
    }
}
