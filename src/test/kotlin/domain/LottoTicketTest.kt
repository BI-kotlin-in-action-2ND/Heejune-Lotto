package domain

import org.example.domain.LottoTicket
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTicketTest {
    @Test
    fun `6개 고유 숫자 로또 티켓 유효성 테스트`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val lottoTicket = LottoTicket(numbers)

        assertDoesNotThrow { lottoTicket.validate() }
    }

    @Test
    fun `6개 미만 숫자 로또 실패 테스트`() {
        val numbers = listOf(1, 2, 3, 4, 5)

        val exception =
            assertThrows<IllegalArgumentException> {
                LottoTicket(numbers).validate()
            }

        assertTrue(exception.message!!.contains("6개여야 합니다"))
    }

    @Test
    fun `중복 숫자 포함 로또 실패 테스트`() {
        val numbers = listOf(1, 2, 3, 4, 5, 5)

        val exception =
            assertThrows<IllegalArgumentException> {
                LottoTicket(numbers).validate()
            }

        assertTrue(exception.message!!.contains("중복될 수 없습니다"))
    }

    @Test
    fun `범위를 벗어난 숫자 포함 로또 실패 테스트`() {
        val numbers = listOf(1, 2, 3, 4, 5, 46) // 46은 유효 범위를 벗어남

        val exception =
            assertThrows<IllegalArgumentException> {
                LottoTicket(numbers).validate()
            }

        assertTrue(exception.message!!.contains("1부터 45 사이의 숫자여야 합니다"))
    }

    @Test
    fun `로또 숫자 매치 테스트`() {
        val ticket = LottoTicket(listOf(1, 2, 3, 4, 5, 6))
        val winningNumbers = listOf(4, 5, 6, 7, 8, 9)
        assertEquals(3, ticket.match(winningNumbers))
    }

    @Test
    fun `로또 보너스 매치 테스트`() {
        val ticket = LottoTicket(listOf(10, 20, 30, 40, 50, 60))
        assertTrue(ticket.hasBonus(30))
    }

    @Test
    fun `로또 보너스 매치 실패 테스트`() {
        val ticket = LottoTicket(listOf(11, 21, 31, 41, 51, 61))
        assertFalse(ticket.hasBonus(30))
    }

    @Test
    fun `로또 번호 정렬 테스트`() {
        val ticket = LottoTicket(listOf(60, 50, 40, 30, 20, 10))
        assertEquals(listOf(10, 20, 30, 40, 50, 60), ticket.sortedNumbers())
    }

    @Test
    fun `로또 번호 출력 포멧 테스트`() {
        val ticket = LottoTicket(listOf(1, 2, 3, 4, 5, 10))
        assertEquals("01, 02, 03, 04, 05, 10", ticket.formattedNumbers())
    }
}
