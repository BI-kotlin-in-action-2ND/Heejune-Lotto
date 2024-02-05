package org.example.domain

class LottoGame(
    private val userLottoTickets: List<LottoTicket>,
    private val winningLotto: WinningLotto,
) {
    fun calculateResults(): Map<PrizeCategory, Int> {
        val result: MutableList<Pair<Int, Boolean>> = mutableListOf()
        userLottoTickets.forEach { lottoTicket ->
            val matchCount = lottoTicket.match(winningLotto.numbers)
            val hasBonus = lottoTicket.hasBonus(winningLotto.bonusNumber)
            result.add(Pair(matchCount, hasBonus))
        }
        return checkRank(result)
    }

    private fun checkRank(results: List<Pair<Int, Boolean>>): Map<PrizeCategory, Int> {
        return results.groupBy { (matchCount, hasBonus) ->
            when {
                matchCount == 6 -> PrizeCategory.FIRST
                matchCount == 5 && hasBonus -> PrizeCategory.SECOND
                matchCount == 5 -> PrizeCategory.THIRD
                matchCount == 4 -> PrizeCategory.FOURTH
                else -> PrizeCategory.NONE
            }
        }.mapValues { (_, value) -> value.size }.filterKeys { it != PrizeCategory.NONE }
    }
}
