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

    fun displayWinningNumbers(
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ) {
        println("\n당첨 번호는 ${winningNumbers.joinToString(", ")} + 보너스 번호 $bonusNumber 입니다.")
    }

    fun displayPrizeResults(results: Map<PrizeCategory, Int>) {
        println(RESULT_LOTTO)
        PrizeCategory.entries.forEachIndexed { index, category ->
            results[category]?.let { count ->
                if (category != PrizeCategory.NONE) {
                    printResult(index + 1, category.prize, count)
                }
            }
        }
    }

    private fun printResult(
        rank: Int,
        prize: Int,
        count: Int,
    ) {
        println("$rank$RANK_BEHIND$prize$RESULT_PRIZE_KW$RESULT_COUNT $count$RESULT_COUNT")
    }

    fun displayTotalPrize(
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
