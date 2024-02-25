package domain.ticket

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.example.domain.ticket.LottoNumber

class LottoNumberTest : StringSpec({
    "로또 번호 생성 테스트" {
        // given
        val number = 1

        // when
        val lottoNumber = LottoNumber(number)

        // then
        lottoNumber.number shouldBe number
    }
})
