package domain

import org.example.config.LottoConfig
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
            }.message!!.contains(LottoConfig.ERROR_MESSAGE_LOTTO_SIZE),
        )
    }

    @Test
    fun `lotto failure for duplicate`() {
        assertTrue(
            assertThrows<IllegalArgumentException> {
                WinningLotto(listOf(1, 2, 3, 4, 5, 5), 7)
            }.message!!.contains(LottoConfig.ERROR_MESSAGE_DUPLICATE),
        )
    }

    @Test
    fun `lotto failure for bonus`() {
        assertTrue(
            assertThrows<IllegalArgumentException> {
                WinningLotto(listOf(1, 2, 3, 4, 5, 6), 6)
            }.message!!.contains(LottoConfig.ERROR_MESSAGE_DUPLICATE),
        )
    }

    @Test
    fun `lotto failure for number over range`() {
        assertTrue(
            assertThrows<IllegalArgumentException> {
                WinningLotto(listOf(1, 2, 3, 4, 5, 46), 7)
            }.message!!.contains(LottoConfig.ERROR_MESSAGE_NUMBER_RANGE),
        )
    }

    @Test
    fun `lotto failure for number under range`() {
        assertTrue(
            assertThrows<IllegalArgumentException> {
                WinningLotto(listOf(0, 2, 3, 4, 5, 6), 7)
            }.message!!.contains(LottoConfig.ERROR_MESSAGE_NUMBER_RANGE),
        )
    }

    @Test
    fun `lotto failure for bonus under range`() {
        assertTrue(
            assertThrows<IllegalArgumentException> {
                WinningLotto(listOf(1, 2, 3, 4, 5, 6), 0)
            }.message!!.contains(LottoConfig.ERROR_MESSAGE_NUMBER_RANGE),
        )
    }

    @Test
    fun `lotto failure for bonus over range`() {
        assertTrue(
            assertThrows<IllegalArgumentException> {
                WinningLotto(listOf(1, 2, 3, 4, 5, 6), 46)
            }.message!!.contains(LottoConfig.ERROR_MESSAGE_NUMBER_RANGE),
        )
    }
}
