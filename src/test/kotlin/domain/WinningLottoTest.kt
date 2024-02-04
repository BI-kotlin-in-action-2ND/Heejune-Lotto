package domain

import org.example.domain.WinningLotto
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `로또 및 보너스 생성 테스트`() {
        assertDoesNotThrow {
            WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        }
    }

    @Test
    fun `로또 번호 실패 테스트`() {
        assertTrue(
            assertThrows<IllegalArgumentException> {
                WinningLotto(listOf(1, 2, 3, 4, 5), 7)
            }.message!!.contains("6개여야 합니다"),
        )

        assertTrue(
            assertThrows<IllegalArgumentException> {
                WinningLotto(listOf(1, 2, 3, 4, 5, 5), 7)
            }.message!!.contains("중복"),
        )

        assertTrue(
            assertThrows<IllegalArgumentException> {
                WinningLotto(listOf(1, 2, 3, 4, 5, 46), 7)
            }.message!!.contains("1부터 45"),
        )

        assertTrue(
            assertThrows<IllegalArgumentException> {
                WinningLotto(listOf(0, 2, 3, 4, 5, 6), 7)
            }.message!!.contains("1부터 45"),
        )
    }
}
