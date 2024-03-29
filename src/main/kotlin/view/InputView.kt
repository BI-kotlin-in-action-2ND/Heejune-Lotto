package org.example.view

import org.example.constant.LottoConstant
import java.util.Locale

object InputView {
    private const val PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요."
    private const val MANUAL_TICKET_COUNT_MESSAGE = "수동으로 구매할 수량을 입력해 주세요."
    private const val MANUAL_TICKET_NUMBERS_MESSAGE = "-- 수동 구매할 로또 번호를 입력해 주세요."
    private const val WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요."
    private const val BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요."
    private const val INVALID_INPUT_MESSAGE = "입력 값이 유효하지 않습니다."
    private const val WINNING_NUMBER_MODE = "당첨 번호를 자동으로 생성하려면 'a', 수동으로 입력하려면 아무 키나 입력하세요."

    fun inputPurchaseAmount(): Int {
        println(PURCHASE_AMOUNT_MESSAGE)
        return readlnOrNull()?.toIntOrNull()?.takeIf { it > 0 } ?: throw IllegalArgumentException(INVALID_INPUT_MESSAGE)
    }

    fun inputManualTicketCount(purchaseMoney: Int): Int {
        println(MANUAL_TICKET_COUNT_MESSAGE)
        return readlnOrNull()?.toIntOrNull()?.takeIf { it in 0..purchaseMoney } ?: throw IllegalArgumentException(
            INVALID_INPUT_MESSAGE,
        )
    }

    fun inputManualTickets(count: Int): List<Set<Int>> {
        println(MANUAL_TICKET_NUMBERS_MESSAGE)
        return (1..count).map {
            requireNotNull(
                readlnOrNull()?.split(',')?.map { input -> input.trim().toInt() }?.let { numbers -> numbers.toSet() },
            ) { INVALID_INPUT_MESSAGE }
        }
    }

    fun inputWinningNumbers(): Set<Int> {
        println(WINNING_NUMBERS_MESSAGE)
        return requireNotNull(
            readlnOrNull()?.split(',')?.map { input -> input.trim().toInt() }?.let { numbers -> numbers.toSet() },
        ) { INVALID_INPUT_MESSAGE }
    }

    fun inputBonusNumber(): Int {
        println(BONUS_NUMBER_MESSAGE)
        return requireNotNull(readlnOrNull()?.toIntOrNull()) { INVALID_INPUT_MESSAGE }
    }

    fun readWinningNumbersMode(): String {
        println(WINNING_NUMBER_MODE)
        return readlnOrNull()?.uppercase(Locale.getDefault()) ?: LottoConstant.BLANK
    }
}
