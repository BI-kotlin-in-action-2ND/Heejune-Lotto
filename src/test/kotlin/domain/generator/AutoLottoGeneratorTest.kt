package domain.generator

import org.example.domain.generator.AutoLottoGenerator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AutoLottoGeneratorTest {
    @Test
    fun `로또 번호 자동 생성 테스트`() {
        val generator = AutoLottoGenerator()

        assertDoesNotThrow { generator.generate() }

        assertTrue(
            generator.generate().formattedNumbers().matches(Regex("^([0-9]{2}, ){5}[0-9]{2}\$")),
            "로또 티켓의 포맷팅이 올바르지 않음",
        )
    }

    @Test
    fun `match 메소드는 주어진 당첨 번호와 일치하는 번호의 개수를 반환한다`() {
        val generator = AutoLottoGenerator()
        val lottoTicket = generator.generate()
        val winningNumbers = lottoTicket.numbers

        // match 메소드의 결과가 기대하는 범위 내에 있는지 확인
        assertEquals(6, lottoTicket.match(winningNumbers))
    }

    @Test
    fun `생성 로또 번호 범위 테스트`() {
        val numberRange = 1..45
        val generator = AutoLottoGenerator(numberRange, 6)
        val lottoTicket = generator.generate()

        assertTrue(lottoTicket.numbers.all { it in numberRange })
    }

    @Test
    fun `생성 로또 번호 중복 테스트`() {
        val generator = AutoLottoGenerator(1..45, 6)
        val lottoTicket = generator.generate()

        assertEquals(lottoTicket.numbers.distinct().size, lottoTicket.numbers.size)
    }

    @Test
    fun `생성 로또 번호 정렬 테스트`() {
        val generator = AutoLottoGenerator(1..45, 6)
        val lottoTicket = generator.generate()

        assertEquals(lottoTicket.numbers, lottoTicket.numbers.sorted())
    }
}
