package org.example.config

import org.example.domain.generator.AutoLottoGenerator
import org.example.domain.generator.ManualLottoGenerator
import org.example.service.LottoService
import org.example.view.InputView
import org.example.view.OutputView

class AppConfig {
    val inputView = InputView
    val outputView = OutputView
    val autoLottoGenerator = AutoLottoGenerator()
    val manualLottoGenerator = ManualLottoGenerator()
    val lottoService = LottoService(this)
}
