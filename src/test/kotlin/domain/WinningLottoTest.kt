package domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldContain
import org.example.constant.LottoConstant
import org.example.domain.WinningLotto
import org.junit.jupiter.api.Assertions.assertDoesNotThrow

class WinningLottoTest : FunSpec({
    context("WinningLotto 유효성 검증") {
        test("로또 및 보너스 생성 테스트") {
            assertDoesNotThrow {
                WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
            }
        }

        test("로또 번호 실패 테스트 - 숫자 개수 미달") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(listOf(1, 2, 3, 4, 5), 7)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_LOTTO_SIZE
        }

        test("로또 번호 실패 테스트 - 중복 숫자") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(listOf(1, 2, 3, 4, 5, 5), 7)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_DUPLICATE
        }

        test("보너스 번호 실패 테스트 - 보너스 번호 중복") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(listOf(1, 2, 3, 4, 5, 6), 6)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_DUPLICATE
        }

        test("로또 번호 실패 테스트 - 범위를 벗어난 숫자") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(listOf(1, 2, 3, 4, 5, 46), 7)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }

        test("로또 번호 실패 테스트 - 범위 아래 숫자") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(listOf(0, 2, 3, 4, 5, 6), 7)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }

        test("보너스 번호 실패 테스트 - 범위 아래 보너스 번호") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(listOf(1, 2, 3, 4, 5, 6), 0)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }

        test("보너스 번호 실패 테스트 - 범위를 벗어난 보너스 번호") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(listOf(1, 2, 3, 4, 5, 6), 46)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }
    }
})
