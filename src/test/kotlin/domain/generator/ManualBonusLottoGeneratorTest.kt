package domain.generator

import org.example.domain.generator.ManualBonusLottoGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ManualBonusLottoGeneratorTest {
    @Test
    fun `generate 생성된 BonusLotto가 올바른 번호와 보너스 번호를 포함하는지 확인`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val generator = ManualBonusLottoGenerator(numbers, bonusNumber)

        val bonusLotto = generator.generate()

        assertEquals(numbers, bonusLotto.numbers)
        assertEquals(bonusNumber, bonusLotto.bonusNumber)
    }

    @Test
    fun `generate 보너스 번호가 로또 번호 목록에 포함된 경우 예외 발생`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 6

        val generator = ManualBonusLottoGenerator(numbers, bonusNumber)

        assertThrows<IllegalArgumentException> {
            generator.generate()
        }.apply {
            assertTrue(message!!.contains("중복"))
        }
    }

    @Test
    fun `generate 보너스 번호가 유효 범위를 벗어난 경우 예외 발생`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 46

        val generator = ManualBonusLottoGenerator(numbers, bonusNumber)

        assertThrows<IllegalArgumentException> {
            generator.generate()
        }.apply {
            assertTrue(message!!.contains("1부터 45"))
        }
    }
}
