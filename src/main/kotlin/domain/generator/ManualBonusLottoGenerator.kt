package org.example.domain.generator

import org.example.domain.BonusLotto

class ManualBonusLottoGenerator(numbers: List<Int>, private val bonusNumber: Int) : ManualLottoGenerator(numbers) {
    override fun generate(): BonusLotto {
        return BonusLotto(numbers, bonusNumber)
    }
}
