package org.example.view

import org.example.domain.LottoTicket
import org.example.domain.PrizeCategory
import org.example.domain.WinningLotto

object OutputView {
    private const val NUMBERS_DELIMITER = ", "
    private const val PREFIX_MESSAGE = "[ "
    private const val SUFFIX_MESSAGE = " ]"
    private const val AUTO_LOTTO_TICKET = "-- 자동 생성 번호 --"
    private const val SUM_MANUAL_AUTO_TICKET = "수동으로 %d장, 자동으로 %d장을 구매했습니다."
    private const val TOTAL_USER_LOTTO = "-- 사용자 로또 번호 --"
    private const val RESULT_LOTTO = "당첨 통계\n---"
    private const val FIRST_MESSAGE = "1등 [6개 번호 일치"
    private const val SECOND_MESSAGE = "2등 [5개 번호 일치, 보너스 번호 일치"
    private const val THIRD_MESSAGE = "3등 [5개 번호 일치"
    private const val FOURTH_MESSAGE = "4등 [4개 번호 일치"
    private const val NONE_MESSAGE = ""
    private const val RESULT_COUNT = "개"
    private const val RESULT_EARNINGS = "총 수익률은"
    private const val RESULT_EARNINGS_PERCENT = "%입니다."
    private const val AUTO_WINNING_NUMBERS = "자동 생성된 당첨 번호:"
    private const val AUTO_BONUS_NUMBER = "보너스 번호:"

    fun displayAutoLottoTicket(autoLottoTickets: List<LottoTicket>) {
        println(AUTO_LOTTO_TICKET)
        autoLottoTickets.forEach {
            println(it.formattedNumbers(NUMBERS_DELIMITER, PREFIX_MESSAGE, SUFFIX_MESSAGE))
        }
    }

    fun displayUserLottoTicket(
        userAllLottoTickets: List<LottoTicket>,
        autoTicketCount: Int,
        manualTicketCount: Int,
    ) {
        println(SUM_MANUAL_AUTO_TICKET.format(autoTicketCount, manualTicketCount))
        println(TOTAL_USER_LOTTO)
        userAllLottoTickets.forEach {
            println(it.formattedNumbers(NUMBERS_DELIMITER, PREFIX_MESSAGE, SUFFIX_MESSAGE))
        }
    }

    fun displayPrizeResults(results: Map<PrizeCategory, Int>) {
        println(RESULT_LOTTO)
        PrizeCategory.entries.forEach { category ->
            if (category != PrizeCategory.NONE) {
                val count = results[category] ?: 0
                val description = getCategoryDescription(category)
                println("$description ${formatPrize(category.prize)}] - $count${RESULT_COUNT}")
            }
        }
    }

    private fun getCategoryDescription(category: PrizeCategory): String {
        return when (category) {
            PrizeCategory.FIRST -> FIRST_MESSAGE
            PrizeCategory.SECOND -> SECOND_MESSAGE
            PrizeCategory.THIRD -> THIRD_MESSAGE
            PrizeCategory.FOURTH -> FOURTH_MESSAGE
            PrizeCategory.NONE -> NONE_MESSAGE
        }
    }

    private fun formatPrize(prize: Int): String {
        return "(%,dKW)".format(prize)
    }

    fun displayTotalEarning(totalEarning: Double) {
        println("$RESULT_EARNINGS ${totalEarning}${RESULT_EARNINGS_PERCENT}")
    }

    fun displayInvalidInput(message: String?) {
        println(message)
    }

    fun displayAutomaticWinningNumbers(winningLotto: WinningLotto) {
        println(AUTO_WINNING_NUMBERS)
        println(winningLotto.formattedNumbers(NUMBERS_DELIMITER, PREFIX_MESSAGE, SUFFIX_MESSAGE))
        println(AUTO_BONUS_NUMBER)
        println(PREFIX_MESSAGE + winningLotto.bonusNumber + SUFFIX_MESSAGE)
    }
}
