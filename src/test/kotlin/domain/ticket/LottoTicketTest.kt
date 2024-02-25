package domain.ticket

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.example.constant.LottoConstant.END_NUMBER
import org.example.constant.LottoConstant.ERROR_MESSAGE_LOTTO_INPUT
import org.example.constant.LottoConstant.LOTTO_TICKET_SIZE
import org.example.constant.LottoConstant.START_NUMBER
import org.example.domain.ticket.LottoNumber
import org.example.domain.ticket.LottoTicket

class LottoTicketTest : StringSpec({
    val numbers = (START_NUMBER until START_NUMBER + LOTTO_TICKET_SIZE).toList()
    val failingNumbers = (START_NUMBER until START_NUMBER + LOTTO_TICKET_SIZE - 1).toList()
    "6개 고유 숫자 로또 티켓 유효성 테스트" {
        val lottoNumber = numbers.map { LottoNumber(it) }.toSet()
        LottoTicket(lottoNumber).numbers shouldBe lottoNumber
    }

    "6개 미만 숫자 로또 실패 테스트" {
        val lottoNumber = failingNumbers.map { LottoNumber(it) }.toSet()
        val exception =
            shouldThrowExactly<IllegalArgumentException> {
                LottoTicket(lottoNumber)
            }
        exception.message shouldContain ERROR_MESSAGE_LOTTO_INPUT
    }

    "중복 숫자 포함 로또 실패 테스트" {
        val lottoNumber = failingNumbers.map { LottoNumber(it) }.toSet().plus(LottoNumber(START_NUMBER))
        val exception =
            shouldThrowExactly<IllegalArgumentException> {
                LottoTicket(lottoNumber)
            }
        exception.message shouldContain ERROR_MESSAGE_LOTTO_INPUT
    }

    "로또 숫자 매치 테스트" {
        val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()

        val ticket = LottoTicket(lottoNumbers)
        val winningLotto = LottoTicket(lottoNumbers)
        ticket.match(winningLotto) shouldBe LOTTO_TICKET_SIZE
    }

    "로또 보너스 매치 테스트" {
        val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()
        val ticket = LottoTicket(lottoNumbers)
        ticket.hasBonus(START_NUMBER).shouldBeTrue()
    }

    "로또 보너스 매치 실패 테스트" {
        val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()
        val ticket = LottoTicket(lottoNumbers)
        ticket.hasBonus(END_NUMBER).shouldBeFalse()
    }

    "로또 번호 출력 포멧 테스트" {
        val lottoNumbers = numbers.map { LottoNumber(it) }.toSet()
        val ticket = LottoTicket(lottoNumbers)
        ticket.formattedNumbers(", ", "", "") shouldBe "01, 02, 03, 04, 05, 06"
    }
})
