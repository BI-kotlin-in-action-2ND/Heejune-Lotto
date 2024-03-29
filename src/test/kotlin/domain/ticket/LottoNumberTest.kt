package domain.ticket

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.example.constant.LottoConstant
import org.example.constant.LottoConstant.LottoMaxNumber
import org.example.constant.LottoConstant.LottoMinNumber
import org.example.domain.ticket.LottoNumber

class LottoNumberTest : StringSpec({
    "로또 번호 생성 테스트" {
        // given
        val number = LottoMinNumber

        // when
        val lottoNumber = LottoNumber(number)

        // then
        lottoNumber.number shouldBe number
    }

    "로또 번호 생성 실패 테스트 - 범위를 벗어난 숫자" {
        // given
        val number = LottoMaxNumber + 1

        // when
        val exception =
            shouldThrow<IllegalArgumentException> {
                LottoNumber(number)
            }

        // then
        exception.message shouldBe LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
    }

    "로또 번호 생성 실패 테스트 - 범위 아래 숫자" {
        // given
        val number = LottoMinNumber - 1

        // when
        val exception =
            shouldThrow<IllegalArgumentException> {
                LottoNumber(number)
            }

        // then
        exception.message shouldBe LottoConstant.ERROR_MESSAGE_NUMBER_RANGE
    }

    "로또 번호 비교 테스트" {
        // given
        val lottoNumber1 = LottoNumber(LottoMinNumber)
        val lottoNumber2 = LottoNumber(LottoMinNumber + 1)

        // when
        val result = lottoNumber1.compareTo(lottoNumber2)

        // then
        result shouldBe -1
    }
})
