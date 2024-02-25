package domain.ticket

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldContain
import org.example.constant.LottoConstant
import org.example.constant.LottoConstant.END_NUMBER
import org.example.constant.LottoConstant.LOTTO_TICKET_SIZE
import org.example.constant.LottoConstant.START_NUMBER
import org.example.domain.ticket.LottoNumber
import org.example.domain.ticket.WinningLotto
import org.junit.jupiter.api.Assertions.assertDoesNotThrow

class WinningLottoTest : FunSpec({
    context("WinningLotto 유효성 검증") {
        val numbers = (START_NUMBER until START_NUMBER + LOTTO_TICKET_SIZE).toList()
        val failingNumbers = (START_NUMBER until START_NUMBER + LOTTO_TICKET_SIZE - 1).toList()

        test("로또 및 보너스 생성 테스트") {
            val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()
            assertDoesNotThrow {
                WinningLotto(lottoNumbers, START_NUMBER + LOTTO_TICKET_SIZE)
            }
        }

        test("로또 번호 실패 테스트 - 숫자 개수 미달") {
            val lottoNumbers = failingNumbers.map { LottoNumber(it) }.toSet()
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, START_NUMBER + LOTTO_TICKET_SIZE)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_LOTTO_INPUT
        }

        test("로또 번호 실패 테스트 - 중복 숫자") {
            val lottoNumbers = numbers.map { LottoNumber(it) }.toSet().plus(LottoNumber(START_NUMBER))
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, 7)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_LOTTO_INPUT
        }

        test("보너스 번호 실패 테스트 - 보너스 번호 중복") {
            val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, START_NUMBER)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_DUPLICATE_BONUS
        }

        test("로또 번호 실패 테스트 - 범위를 벗어난 숫자") {
            val lottoNumbers = failingNumbers.map { LottoNumber(it) }.toSet().plus(LottoNumber(END_NUMBER + 1))
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, START_NUMBER + LOTTO_TICKET_SIZE)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }

        test("로또 번호 실패 테스트 - 범위 아래 숫자") {
            val lottoNumbers = failingNumbers.map { LottoNumber(it) }.toSet().plus(LottoNumber(START_NUMBER - 1))
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, START_NUMBER + LOTTO_TICKET_SIZE)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }

        test("보너스 번호 실패 테스트 - 범위 아래 보너스 번호") {
            val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, START_NUMBER - 1)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }

        test("보너스 번호 실패 테스트 - 범위를 벗어난 보너스 번호") {
            val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()
            val exception =
                shouldThrowExactly<IllegalArgumentException> {
                    WinningLotto(lottoNumbers, END_NUMBER + 1)
                }
            exception.message shouldContain LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
        }
    }
})
