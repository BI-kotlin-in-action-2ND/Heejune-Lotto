package org.example.controller

import org.example.domain.LottoTicket
import org.example.domain.WinningLotto
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

    fun inputManualTickets(manualTicketCount: Int): List<LottoTicket> {
        if (manualTicketCount == 0) return emptyList()

        try {
            return inputView.inputManualTickets(manualTicketCount).map { lottoService.manualLottoGenerator(it) }
                .toList()
        } catch (e: IllegalArgumentException) {
            outputView.displayInvalidInput(e.message)
            return inputManualTickets(manualTicketCount)
        }
    }

    fun inputWinningNumbers(winningMode: String): WinningLotto {
        if (winningMode == "A") {
            return lottoService.autoWinningLottoGenerator().also { outputView.displayAutomaticWinningNumbers(it) }
        }

        try {
            val winningLotto = inputView.inputWinningNumbers()
            val bonusNumber = inputView.inputBonusNumber()
            return WinningLotto(winningLotto, bonusNumber)
        } catch (e: IllegalArgumentException) {
            outputView.displayInvalidInput(e.message)
            return inputWinningNumbers(winningMode)
        }
    }
}
