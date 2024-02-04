package org.example.view

import org.example.domain.LottoTicket
import org.example.domain.PrizeCategory

object OutputView {
    private const val NUMBERS_DELIMITER = ", "
    private const val PREFIX_MESSAGE = "[ "
    private const val SUFFIX_MESSAGE = " ]"
    private const val AUTO_LOTTO_TICKET = "-- 자동 생성 번호 --"
    private const val SUM_MANUAL_AUTO_TICKET = "수동으로 %d장, 자동으로 %d장을 구매했습니다."
    private const val TOTAL_USER_LOTTO = "-- 사용자 로또 번호 --"
    private const val RESULT_LOTTO = "당첨 통계\n---"
    private const val RANK_BEHIND = "등 ("
    private const val RESULT_PRIZE_KW = " KW) -"
    private const val RESULT_COUNT = "개"
    private const val RESULT_EARNINGS = "총 수익률은"
    private const val RESULT_EARNINGS_PERCENT = "%입니다."

    fun displayAutoLottoTicket(autoLottoTickets: List<LottoTicket>) {
        println(AUTO_LOTTO_TICKET)
        autoLottoTickets.forEach {
            println(
                it.numbers.joinToString(NUMBERS_DELIMITER, PREFIX_MESSAGE, SUFFIX_MESSAGE),
            )
        }
    }

    fun displayUserLottoTicket(
        userLottoTickets: List<LottoTicket>,
        userManualLottoTickets: List<LottoTicket>,
    ) {
        println(SUM_MANUAL_AUTO_TICKET.format(userManualLottoTickets.size, userLottoTickets.size))
        println(TOTAL_USER_LOTTO)
        userLottoTickets.forEach { println(it.numbers.joinToString(NUMBERS_DELIMITER, PREFIX_MESSAGE, SUFFIX_MESSAGE)) }
    }

    fun displayPrizeResults(results: Map<PrizeCategory, Int>): Int {
        var totalPrize = 0
        println(RESULT_LOTTO)
        PrizeCategory.entries.forEach { category ->
            if (category != PrizeCategory.NONE) {
                val count = results[category] ?: 0
                val description = getCategoryDescription(category)
                println("$description ${formatPrize(category.prize)}] - $count${RESULT_COUNT}")
                totalPrize += category.prize * count
            }
        }
        return totalPrize
    }

    private fun getCategoryDescription(category: PrizeCategory): String {
        return when (category) {
            PrizeCategory.FIRST -> "1등 [6개 번호 일치"
            PrizeCategory.SECOND -> "2등 [5개 번호 일치, 보너스 번호 일치"
            PrizeCategory.THIRD -> "3등 [5개 번호 일치"
            PrizeCategory.FOURTH -> "4등 [4개 번호 일치"
            PrizeCategory.NONE -> ""
        }
    }

    private fun formatPrize(prize: Int): String {
        return "(%,dKW)".format(prize)
    }

    fun displayTotalEarning(
        totalPrize: Int,
        purchaseAmount: Int,
    ) {
        val profitRate = (totalPrize.toDouble() / purchaseAmount * 100).toInt()
        println("\n$RESULT_EARNINGS $profitRate$RESULT_EARNINGS_PERCENT")
    }

    fun displayInvalidInput(message: String?) {
        println(message)
    }
}