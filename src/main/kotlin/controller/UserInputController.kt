package org.example.controller

import org.example.constant.LottoConstant
import org.example.domain.LottoTickets
import org.example.domain.WinningLotto
import org.example.domain.ticket.LottoNumber
import org.example.service.LottoService
import org.example.view.InputView
import org.example.view.OutputView

class UserInputController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoService: LottoService,
) {
    fun inputManualTicketCount(purchaseMoney: Int): Int {
        try {
            return inputView.inputManualTicketCount(purchaseMoney)
        } catch (e: IllegalArgumentException) {
            outputView.displayInvalidInput(e.message)
            return inputManualTicketCount(purchaseMoney)
        }
    }

    fun inputPurchaseAmount(remainMoney: Int): Int {
        if (remainMoney != 0) {
            return remainMoney
        }

        try {
            val purchase = inputView.inputPurchaseAmount()
            return purchase
        } catch (e: IllegalArgumentException) {
            outputView.displayInvalidInput(e.message)
            return inputPurchaseAmount(remainMoney)
        }
    }

    fun inputManualTickets(manualTicketCount: Int): LottoTickets {
        if (manualTicketCount == 0) return LottoTickets(emptyList())

        try {
            val manualInput = inputView.inputManualTickets(manualTicketCount)
            return lottoService.manualLottoGenerator(manualInput)
        } catch (e: IllegalArgumentException) {
            outputView.displayInvalidInput(e.message)
            return inputManualTickets(manualTicketCount)
        }
    }

    fun inputWinningNumbers(winningMode: String): WinningLotto {
        if (winningMode == LottoConstant.AUTO_MODE) {
            return lottoService.autoWinningLottoGenerator().also { outputView.displayAutomaticWinningNumbers(it) }
        }

        try {
            val winningNumbers = inputView.inputWinningNumbers()
            val bonusNumber = inputView.inputBonusNumber()

            val winningLotto = winningNumbers.map { LottoNumber(it) }.toSet()
            return WinningLotto(winningLotto, bonusNumber)
        } catch (e: IllegalArgumentException) {
            outputView.displayInvalidInput(e.message)
            return inputWinningNumbers(winningMode)
        }
    }
}
