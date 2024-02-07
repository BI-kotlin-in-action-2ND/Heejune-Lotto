package org.example.controller

import org.example.config.AppConfig
import org.example.domain.LottoResult
import org.example.domain.LottoResultCalculator
import org.example.domain.LottoTickets
import org.example.service.LottoService
import org.example.view.InputView
import org.example.view.OutputView

class GameController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoService: LottoService,
    private val userInputController: UserInputController,
) {
    constructor(config: AppConfig) : this(
        config.inputView,
        config.outputView,
        config.lottoService,
        config.userInputController,
    )

    fun start(remainMoney: Int = 0): Int {
        // 구매금액 입력
        val purchaseMoney = userInputController.inputPurchaseAmount(remainMoney)
        // 수동으로 구매할 티켓 수 입력
        val manualTicketCount = userInputController.inputManualTicketCount(purchaseMoney)
        // 수동으로 구매할 티켓 번호 입력
        val manualTickets = userInputController.inputManualTickets(manualTicketCount)
        // 자동 생성된 티켓 출력
        val autoLottoTickets = generateAutoLotto(purchaseMoney - manualTicketCount)
        // 전체 로또 티켓 출력
        val allLottoTickets = printAllLottoTickets(manualTickets, autoLottoTickets)
        // 당첨 번호 및 보너스 번호 입력 모드
        val winningMode = selectWinningMode()
        // 당첨 번호 입력
        // 보너스 번호 입력
        val winningLotto = userInputController.inputWinningNumbers(winningMode)
        // 결과 출력
        val lottoResultCalculatorResult =
            LottoResultCalculator(
                allLottoTickets,
                winningLotto,
            ).calculateResults()
        printResult(lottoResultCalculatorResult, purchaseMoney)
        return outputView.remainMoney(lottoService.earningMoney(lottoResultCalculatorResult))
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
        lottoResult: LottoResult,
        purchaseMoney: Int,
    ) {
        outputView.displayPrizeResults(lottoResult)
        outputView.displayTotalEarning(
            lottoService.calculateTotalEarningPercentage(
                purchaseMoney,
                lottoResult,
            ),
        )
    }

    private fun printAllLottoTickets(
        manualTickets: LottoTickets,
        autoLottoTickets: LottoTickets,
    ): LottoTickets {
        outputView.displayUserLottoTicket(
            manualTickets + autoLottoTickets,
            manualTickets.size,
            autoLottoTickets.size,
        )
        return manualTickets + autoLottoTickets
    }

    private fun generateAutoLotto(autoLottoTickets: Int): LottoTickets {
        val autoTickets = lottoService.autoLottoGenerator(autoLottoTickets)
        outputView.displayAutoLottoTicket(autoTickets)
        return autoTickets
    }
}
