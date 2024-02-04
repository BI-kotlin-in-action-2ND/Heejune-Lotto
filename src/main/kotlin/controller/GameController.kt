package org.example.controller

import org.example.config.AppConfig
import org.example.domain.LottoGame
import org.example.domain.LottoTicket
import org.example.domain.PrizeCategory
import org.example.domain.WinningLotto
import org.example.domain.generator.AutoLottoGenerator
import org.example.view.InputView
import org.example.view.OutputView

class GameController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val autoLottoGenerator: AutoLottoGenerator,
) {
    constructor(config: AppConfig) : this(
        config.inputView,
        config.outputView,
        config.autoLottoGenerator,
    )

    fun start() {
        // 구매금액 입력
        val purchaseMoney = purchaseAmount()
        // 수동으로 구매할 티켓 수 입력
        val manualTicketCount = purchaseManualTicketCount()
        // 수동으로 구매할 티켓 번호 입력
        val manualTickets = manualTicketCount.let { inputView.inputManualTickets(it) }
        // 자동 생성된 티켓 출력
        val autoLottoTickets = printAutoTickets(purchaseMoney - manualTicketCount)
        // 전체 로또 티켓 출력
        val allLottoTickets = printAllLottoTickets(manualTickets, autoLottoTickets)
        // 당첨 번호 입력
        // 보너스 번호 입력
        val winningLotto = lotteWinningNumbers()
        // 결과 출력
        val lottoGameResult =
            LottoGame(
                allLottoTickets,
                winningLotto,
            ).calculateResults()
        val totalResult = processResults(lottoGameResult)
        outputView.displayPrizeResults(totalResult)
        outputView.displayTotalEarning(calculateTotalEarning(totalResult, purchaseMoney))
    }

    private fun calculateTotalEarning(
        results: Map<PrizeCategory, Int>,
        purchaseMoney: Int,
    ): Double {
        val totalEarning =
            results.map { (category, count) ->
                category.prize * count
            }.sum()
        return totalEarning.toDouble() / purchaseMoney
    }

    private fun processResults(results: List<Pair<Int, Boolean>>): Map<PrizeCategory, Int> {
        return results.groupBy { (matchCount, hasBonus) ->
            when {
                matchCount == 6 -> PrizeCategory.FIRST
                matchCount == 5 && hasBonus -> PrizeCategory.SECOND
                matchCount == 5 -> PrizeCategory.THIRD
                matchCount == 4 -> PrizeCategory.FOURTH
                else -> PrizeCategory.NONE
            }
        }.mapValues { (_, value) -> value.size }.filterKeys { it != PrizeCategory.NONE }
    }

    private fun lotteWinningNumbers(): WinningLotto {
        try {
            val winningLotto = inputView.inputWinningNumbers()
            val bonusNumber = inputView.inputBonusNumber()
            return WinningLotto(winningLotto, bonusNumber)
        } catch (e: IllegalArgumentException) {
            outputView.displayInvalidInput(e.message)
            return lotteWinningNumbers()
        }
    }

    private fun printAllLottoTickets(
        manualTickets: List<LottoTicket>,
        autoLottoTickets: List<LottoTicket>,
    ): List<LottoTicket> {
        outputView.displayUserLottoTicket(autoLottoTickets, manualTickets)
        return manualTickets + autoLottoTickets
    }

    private fun printAutoTickets(autoLottoTickets: Int): List<LottoTicket> {
        val autoTickets = (1..autoLottoTickets).map { autoLottoGenerator.generate() }
        outputView.displayAutoLottoTicket(autoTickets)
        return autoTickets
    }

    private fun purchaseManualTicketCount(): Int {
        try {
            return inputView.inputManualTicketCount()
        } catch (e: IllegalArgumentException) {
            outputView.displayInvalidInput(e.message)
            return purchaseManualTicketCount()
        }
    }

    private fun purchaseAmount(): Int {
        try {
            val purchase = inputView.inputPurchaseAmount()
            return purchase
        } catch (e: IllegalArgumentException) {
            outputView.displayInvalidInput(e.message)
            return purchaseAmount()
        }
    }
}
