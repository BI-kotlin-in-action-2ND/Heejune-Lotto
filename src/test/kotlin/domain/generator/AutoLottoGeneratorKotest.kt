package domain.generator

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch
import org.example.config.LottoConfig
import org.example.domain.generator.AutoLottoGenerator

class AutoLottoGeneratorKotest : FunSpec({

    val generator = AutoLottoGenerator()

    test("로또 번호 자동 생성 테스트") {
        val generatedLottoTicket = generator.generate()
        val formattedNumbers = generatedLottoTicket.formattedNumbers(", ", "[", "]")

        formattedNumbers shouldMatch Regex("^\\[\\d{2}, \\d{2}, \\d{2}, \\d{2}, \\d{2}, \\d{2}\\]$")
    }

    test("match 메소드는 주어진 당첨 번호와 일치하는 번호의 개수를 반환한다") {
        val lottoTicket = generator.generate()
        val winningNumbers = lottoTicket.numbers

        lottoTicket.match(winningNumbers) shouldBeExactly LottoConfig.LOTTO_TICKET_SIZE
    }

    test("생성 로또 번호 범위 테스트") {
        val numberRange = LottoConfig.START_NUMBER..LottoConfig.END_NUMBER
        val lottoTicket = AutoLottoGenerator().generate()

        lottoTicket.numbers.all { it in numberRange }.shouldBeTrue()
    }

    test("생성 로또 번호 중복 테스트") {
        val lottoTicket = generator.generate()

        lottoTicket.numbers.distinct().size shouldBe lottoTicket.numbers.size
    }

    test("생성 로또 번호 정렬 테스트") {
        val lottoTicket = generator.generate()

        lottoTicket.numbers.shouldBeSorted()
    }
})
