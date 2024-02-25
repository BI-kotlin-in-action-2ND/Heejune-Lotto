package domain.ticket

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.example.domain.ticket.LottoNumber
import org.example.domain.ticket.LottoTicket

class LottoTicketTest : StringSpec({
    "6개 고유 숫자 로또 티켓 유효성 테스트" {
        val numbers = listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()
        LottoTicket(numbers).numbers shouldBe numbers
    }

    "6개 미만 숫자 로또 실패 테스트" {
        val numbers = listOf(1, 2, 3, 4, 5).map { LottoNumber(it) }.toSet()
        val exception =
            shouldThrowExactly<IllegalArgumentException> {
                LottoTicket(numbers)
            }
        exception.message shouldContain "로또 번호는 6개이고, 중복되지 않는 숫자여야 합니다."
    }

    "중복 숫자 포함 로또 실패 테스트" {
        val numbers = listOf(1, 2, 3, 4, 5, 5).map { LottoNumber(it) }.toSet()
        val exception =
            shouldThrowExactly<IllegalArgumentException> {
                LottoTicket(numbers)
            }
        exception.message shouldContain "로또 번호는 6개이고, 중복되지 않는 숫자여야 합니다."
    }

    "범위를 벗어난 숫자 포함 로또 실패 테스트" {
        val exception =
            shouldThrowExactly<IllegalArgumentException> {
                val numbers = listOf(1, 2, 3, 4, 5, 46).map { LottoNumber(it) }.toSet()
                LottoTicket(numbers)
            }
        exception.message shouldContain "1부터 45"
    }

    "로또 숫자 매치 테스트" {
        val lottoNumbers = listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()
        val winningNumbers = listOf(4, 5, 6, 7, 8, 9).map { LottoNumber(it) }.toSet()

        val ticket = LottoTicket(lottoNumbers)
        val winningLotto = LottoTicket(winningNumbers)
        ticket.match(winningLotto) shouldBe 3
    }

    "로또 보너스 매치 테스트" {
        val lottoNumbers = listOf(10, 20, 30, 40, 41, 42).map { LottoNumber(it) }.toSet()
        val ticket = LottoTicket(lottoNumbers)
        ticket.hasBonus(30).shouldBeTrue()
    }

    "로또 보너스 매치 실패 테스트" {
        val lottoNumbers = listOf(11, 21, 31, 41, 42, 43).map { LottoNumber(it) }.toSet()
        val ticket = LottoTicket(lottoNumbers)
        ticket.hasBonus(30).shouldBeFalse()
    }

    "로또 번호 출력 포멧 테스트" {
        val lottoNumbers = listOf(1, 2, 3, 4, 5, 10).map { LottoNumber(it) }.toSet()
        val ticket = LottoTicket(lottoNumbers)
        ticket.formattedNumbers(", ", "", "") shouldBe "01, 02, 03, 04, 05, 10"
    }
})
