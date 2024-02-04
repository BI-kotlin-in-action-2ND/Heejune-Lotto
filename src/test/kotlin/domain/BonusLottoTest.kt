package domain

import org.example.domain.BonusLotto
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BonusLottoTest {
    @Test
    fun `로또 및 보너스 생성 테스트`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7

        assertDoesNotThrow { BonusLotto(numbers, bonusNumber) }
    }

    @Test
    fun `로또 번호 실패 테스트`() {
        assertTrue(
            assertThrows<IllegalArgumentException> {
                BonusLotto(listOf(1, 2, 3, 4, 5), 7)
            }.message!!.contains("6개여야 합니다"),
        )

        assertTrue(
            assertThrows<IllegalArgumentException> {
                BonusLotto(listOf(1, 2, 3, 4, 5, 5), 7)
            }.message!!.contains("중복"),
        )

        assertTrue(
            assertThrows<IllegalArgumentException> {
                BonusLotto(listOf(1, 2, 3, 4, 5, 46), 7)
            }.message!!.contains("1부터 45"),
        )

        assertTrue(
            assertThrows<IllegalArgumentException> {
                BonusLotto(listOf(0, 2, 3, 4, 5, 45), 7)
            }.message!!.contains("1부터 45"),
        )
    }

    @Test
    fun `로또 번호와 보너스 번호 중복 실패 테스트`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 6

        val exception = assertThrows<IllegalArgumentException> { BonusLotto(numbers, bonusNumber) }
        assertTrue(exception.message!!.contains("중복"))
    }

    @Test
    fun `보너스 번호 범위 실패 테스트`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 46

        val exception = assertThrows<IllegalArgumentException> { BonusLotto(numbers, bonusNumber) }
        assertTrue(exception.message!!.contains("1부터 45"))
    }
}
