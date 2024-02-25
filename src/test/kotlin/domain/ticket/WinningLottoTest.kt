package domain.ticket

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldContain
import org.example.constant.LottoConstant
import org.example.domain.ticket.LottoNumber
import org.example.domain.ticket.WinningLotto
import org.junit.jupiter.api.Assertions.assertDoesNotThrow

class WinningLottoTest : FunSpec({
    context("WinningLotto 유효성 검증") {
        test("로또 및 보너스 생성 테스트") {
            val lottoNumbers = listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()
            assertDoesNotThrow {
                WinningLotto(lottoNumbers, 7)
            }
        }

        test("로또 번호 실패 테스트 - 숫자 개수 미달") {
            val lottoNumbers = listOf(1, 2, 3, 4, 5).map { LottoNumber(it) }.toSet()
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, 7)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_LOTTO_INPUT
        }

        test("로또 번호 실패 테스트 - 중복 숫자") {
            val lottoNumbers = listOf(1, 2, 3, 4, 5, 5).map { LottoNumber(it) }.toSet()
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, 7)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_LOTTO_INPUT
        }

        test("보너스 번호 실패 테스트 - 보너스 번호 중복") {
            val lottoNumbers = listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, 6)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_DUPLICATE_BONUS
        }

        test("로또 번호 실패 테스트 - 범위를 벗어난 숫자") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    val lottoNumbers = listOf(1, 2, 3, 4, 5, 46).map { LottoNumber(it) }.toSet()
                    WinningLotto(lottoNumbers, 7)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }

        test("로또 번호 실패 테스트 - 범위 아래 숫자") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    val lottoNumbers = listOf(0, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()
                    WinningLotto(lottoNumbers, 7)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }

        test("보너스 번호 실패 테스트 - 범위 아래 보너스 번호") {
            val lottoNumbers = listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, 0)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }

        test("보너스 번호 실패 테스트 - 범위를 벗어난 보너스 번호") {
            val lottoNumbers = listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, 46)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }
    }
})
