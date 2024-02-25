package org.example

import org.example.domain.LottoResult
import org.example.domain.PrizeCategory

class LottoCheckRank(private val results: List<Pair<Int, Boolean>>) {
    fun checkRank(): LottoResult {
        val lottoResult =
            results.groupBy { (matchCount, hasBonus) ->
                PrizeCategory.getRank(matchCount, hasBonus)
            }.mapValues { (_, value) -> value.size }.filterKeys { it != PrizeCategory.NONE }

        return LottoResult(lottoResult)
    }
}