package domain.generator

import org.example.domain.generator.ManualLottoGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ManualLottoGeneratorTest {
    @Test
    fun `수동 로또 번호 생성 테스트`() {
        val validNumbers = listOf(listOf(1, 2, 3, 4, 5, 6))
        val generator = ManualLottoGenerator()

        val lottoTicket = generator.generate(validNumbers)

        assertEquals(lottoTicket.get(0).numbers, validNumbers[0])
    }

    @Test
    fun `수동 로또 번호 생성 중복 실패 테스트`() {
        val invalidNumbers = listOf(listOf(1, 2, 3, 4, 5, 5))

        val generator = ManualLottoGenerator()

        assertThrows<IllegalArgumentException> {
            generator.generate(invalidNumbers)
        }
    }

    @Test
    fun `수동 로또 번호 생성 범위 실패 테스트`() {
        val outOfRangeNumbers = listOf(listOf(0, 2, 3, 4, 5, 46))

        val generator = ManualLottoGenerator()

        assertThrows<IllegalArgumentException> {
            generator.generate(outOfRangeNumbers)
        }
    }

    @Test
    fun `수동 로또 번호 생성 갯수 실패 테스트`() {
        val lessThanSixNumbers = listOf(listOf(1, 2, 3, 4, 5))

        val generator = ManualLottoGenerator()

        assertThrows<IllegalArgumentException> {
            generator.generate(lessThanSixNumbers)
        }
    }
}
