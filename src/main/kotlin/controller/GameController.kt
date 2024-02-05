package org.example.controller

import org.example.config.AppConfig
import org.example.domain.LottoGame
import org.example.domain.LottoTicket
import org.example.domain.PrizeCategory
import org.example.domain.WinningLotto
import org.example.service.LottoService
import org.example.view.InputView
import org.example.view.OutputView

class GameController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoService: LottoService,
) {
    constructor(config: AppConfig) : this(
        config.inputView,
        config.outputView,
        config.lottoService,
    )

    fun start(remainMoney: Int = 0): Int {
        // 구매금액 입력
        val purchaseMoney = inputPurchaseAmount(remainMoney)
        // 수동으로 구매할 티켓 수 입력
        val manualTicketCount = inputManualTicketCount(purchaseMoney)
        // 수동으로 구매할 티켓 번호 입력
        val manualTickets = inputManualTickets(manualTicketCount)
        // 자동 생성된 티켓 출력
        val autoLottoTickets = generateAutoLotto(purchaseMoney - manualTicketCount)
        // 전체 로또 티켓 출력
        val allLottoTickets = printAllLottoTickets(manualTickets, autoLottoTickets)
        // 당첨 번호 및 보너스 번호 입력 모드
        val winningMode = selectWinningMode()
        // 당첨 번호 입력
        // 보너스 번호 입력
        val winningLotto = inputWinningNumbers(winningMode)
        // 결과 출력
        val lottoGameResult =
            LottoGame(
                allLottoTickets,
                winningLotto,
            ).calculateResults()
        printResult(lottoGameResult, purchaseMoney)
        return lottoService.earningMoney(lottoGameResult)
    }

    private fun selectWinningMode(): String {
        try {
            return inputView.readWinningNumbersMode()
        } catch (e: IllegalArgumentException) {
            outputView.displayInvalidInput(e.message)
            return selectWinningMode()
        }
    }

    private fun printResult(
        totalResult: Map<PrizeCategory, Int>,
        purchaseMoney: Int,
    ) {
        outputView.displayPrizeResults(totalResult)
        outputView.displayTotalEarning(lottoService.calculateTotalEarningPercentage(purchaseMoney, totalResult))
    }

    private fun inputManualTickets(manualTicketCount: Int): List<LottoTicket> {
        if (manualTicketCount == 0) return emptyList()

        try {
            return inputView.inputManualTickets(manualTicketCount).map { lottoService.manualLottoGenerator(it) }
                .toList()
        } catch (e: IllegalArgumentException) {
            outputView.displayInvalidInput(e.message)
            return inputManualTickets(manualTicketCount)
        }
    }

    private fun inputWinningNumbers(winningMode: String): WinningLotto {
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

    private fun printAllLottoTickets(
        manualTickets: List<LottoTicket>,
        autoLottoTickets: List<LottoTicket>,
    ): List<LottoTicket> {
        outputView.displayUserLottoTicket(
            manualTickets.plus(autoLottoTickets),
            manualTickets.size,
            autoLottoTickets.size,
        )
        return manualTickets + autoLottoTickets
    }

    private fun generateAutoLotto(autoLottoTickets: Int): List<LottoTicket> {
        val autoTickets = lottoService.autoLottoGenerator(autoLottoTickets)
        outputView.displayAutoLottoTicket(autoTickets)
        return autoTickets
    }

    private fun inputManualTicketCount(purchaseMoney: Int): Int {
        try {
            return inputView.inputManualTicketCount(purchaseMoney)
        } catch (e: IllegalArgumentException) {
            outputView.displayInvalidInput(e.message)
            return inputManualTicketCount(purchaseMoney)
        }
    }

    private fun inputPurchaseAmount(remainMoney: Int): Int {
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
}
