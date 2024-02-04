package org.example.view

import org.example.domain.LottoTicket

object InputView {
    private const val PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요."
    private const val MANUAL_TICKET_COUNT_MESSAGE = "수동으로 구매할 수량을 입력해 주세요."
    private const val MANUAL_TICKET_NUMBERS_MESSAGE = "-- 수동 구매할 로또 번호를 입력해 주세요."
    private const val WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요."
    private const val BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요."
    private const val INVALID_INPUT_MESSAGE = "입력 값이 유효하지 않습니다."

    fun inputPurchaseAmount(): Int {
        println(PURCHASE_AMOUNT_MESSAGE)
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException(INVALID_INPUT_MESSAGE)
    }

    fun inputManualTicketCount(): Int {
        println(MANUAL_TICKET_COUNT_MESSAGE)
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException(INVALID_INPUT_MESSAGE)
    }

    fun inputManualTickets(count: Int): List<LottoTicket> {
        println(MANUAL_TICKET_NUMBERS_MESSAGE)
        return (1..count).map { readln().split(',') }
            .map { it.map { it.trim().toIntOrNull() ?: throw IllegalArgumentException(INVALID_INPUT_MESSAGE) } }
            .map { LottoTicket(it) }
    }

    fun inputWinningNumbers(): LottoTicket {
        println(WINNING_NUMBERS_MESSAGE)
        return readln().split(',')
            .map { it.trim().toIntOrNull() ?: throw IllegalArgumentException(INVALID_INPUT_MESSAGE) }
            .let { LottoTicket(it) }
    }

    fun inputBonusNumber(): Int {
        println(BONUS_NUMBER_MESSAGE)
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException(INVALID_INPUT_MESSAGE)
    }
}
