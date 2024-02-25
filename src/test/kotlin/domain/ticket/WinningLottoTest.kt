package domain.ticket

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldContain
import org.example.constant.LottoConstant
import org.example.constant.LottoConstant.LOTTO_TICKET_SIZE
import org.example.constant.LottoConstant.LottoMaxNumber
import org.example.constant.LottoConstant.LottoMinNumber
import org.example.domain.ticket.LottoNumber
import org.example.domain.ticket.WinningLotto
import org.junit.jupiter.api.Assertions.assertDoesNotThrow

class WinningLottoTest : FunSpec({
    context("WinningLotto 유효성 검증") {
        val numbers = (LottoMinNumber until LottoMinNumber + LOTTO_TICKET_SIZE).toList()
        val failingNumbers = (LottoMinNumber until LottoMinNumber + LOTTO_TICKET_SIZE - 1).toList()

        test("로또 및 보너스 생성 테스트") {
            val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()
            assertDoesNotThrow {
                WinningLotto(lottoNumbers, LottoMinNumber + LOTTO_TICKET_SIZE)
            }
        }

        test("로또 번호 실패 테스트 - 숫자 개수 미달") {
            val lottoNumbers = failingNumbers.map { LottoNumber(it) }.toSet()
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, LottoMinNumber + LOTTO_TICKET_SIZE)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_LOTTO_INPUT
        }

        test("보너스 번호 실패 테스트 - 보너스 번호 중복") {
            val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, LottoMinNumber)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_DUPLICATE_BONUS
        }

        test("로또 번호 실패 테스트 - 범위를 벗어난 숫자") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    val lottoNumbers = failingNumbers.map { LottoNumber(it) }.toSet().plus(LottoNumber(LottoMaxNumber + 1))
                    WinningLotto(lottoNumbers, LottoMinNumber + LOTTO_TICKET_SIZE)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }

        test("로또 번호 실패 테스트 - 범위 아래 숫자") {
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    val lottoNumbers = failingNumbers.map { LottoNumber(it) }.toSet().plus(LottoNumber(LottoMinNumber - 1))
                    WinningLotto(lottoNumbers, LottoMinNumber + LOTTO_TICKET_SIZE)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }

        test("보너스 번호 실패 테스트 - 범위 아래 보너스 번호") {
            val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, LottoMinNumber - 1)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }

        test("보너스 번호 실패 테스트 - 범위를 벗어난 보너스 번호") {
            val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, LottoMaxNumber + 1)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }
    }
})
