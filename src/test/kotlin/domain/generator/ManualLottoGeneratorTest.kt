package domain.generator

import org.example.domain.generator.ManualLottoGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ManualLottoGeneratorTest {
    @Test
    fun `수동 로또 번호 생성 테스트`() {
        val validNumbers = listOf(1, 2, 3, 4, 5, 6)
        val generator = ManualLottoGenerator(validNumbers)

        val lottoTicket = generator.generate()

        assertEquals(validNumbers, lottoTicket.numbers)
    }

    @Test
    fun `수동 로또 번호 생성 중복 실패 테스트`() {
        val invalidNumbers = listOf(1, 2, 3, 4, 5, 5)

        val generator = ManualLottoGenerator(invalidNumbers)

        assertThrows<IllegalArgumentException> {
            generator.generate()
        }
    }

    @Test
    fun `수동 로또 번호 생성 범위 실패 테스트`() {
        val outOfRangeNumbers = listOf(0, 2, 3, 4, 5, 46)

        val generator = ManualLottoGenerator(outOfRangeNumbers)

        assertThrows<IllegalArgumentException> {
            generator.generate()
        }
    }

    @Test
    fun `수동 로또 번호 생성 갯수 실패 테스트`() {
        val lessThanSixNumbers = listOf(1, 2, 3, 4, 5)

        val generator = ManualLottoGenerator(lessThanSixNumbers)

        assertThrows<IllegalArgumentException> {
            generator.generate()
        }
    }
}
