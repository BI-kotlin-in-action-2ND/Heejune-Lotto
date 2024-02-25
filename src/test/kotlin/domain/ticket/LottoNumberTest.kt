package domain.ticket

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.example.constant.LottoConstant
import org.example.constant.LottoConstant.END_NUMBER
import org.example.constant.LottoConstant.START_NUMBER
import org.example.domain.ticket.LottoNumber

class LottoNumberTest : StringSpec({
    "로또 번호 생성 테스트" {
        // given
        val number = START_NUMBER

        // when
        val lottoNumber = LottoNumber(number)

        // then
        lottoNumber.number shouldBe number
    }

    "로또 번호 생성 실패 테스트 - 범위를 벗어난 숫자" {
        // given
        val number = END_NUMBER + 1

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
        val number = START_NUMBER - 1

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
        val lottoNumber1 = LottoNumber(START_NUMBER)
        val lottoNumber2 = LottoNumber(START_NUMBER + 1)

        // when
        val result = lottoNumber1.compareTo(lottoNumber2)

        // then
        result shouldBe -1
    }
})
