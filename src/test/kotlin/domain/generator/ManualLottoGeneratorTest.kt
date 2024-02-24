package domain.generator

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.example.domain.generator.ManualLottoGenerator


class ManualLottoGeneratorTest : StringSpec({
    val generator = ManualLottoGenerator()

    "수동 로또 번호 생성 테스트" {
        val validNumbers = setOf(1, 2, 3, 4, 5, 6)
        val lottoTicket = generator.generate(validNumbers)
        lottoTicket.numbers shouldBe validNumbers
    }

    "수동 로또 번호 생성 중복 실패 테스트" {
        val invalidNumbers = setOf(1, 2, 3, 4, 5, 5)
        shouldThrowExactly<IllegalArgumentException> {
            generator.generate(invalidNumbers)
        }
    }

    "수동 로또 번호 생성 범위 실패 테스트" {
        val outOfRangeNumbers = setOf(1, 2, 3, 4, 5, 46)
        shouldThrowExactly<IllegalArgumentException> {
            generator.generate(outOfRangeNumbers)
        }
    }

    "수동 로또 번호 생성 갯수 실패 테스트" {
        val lessThanSixNumbers = setOf(1, 2, 3, 4, 5)
        shouldThrowExactly<IllegalArgumentException> {
            generator.generate(lessThanSixNumbers)
        }
    }

    "수동 로또 티켓 생성 테스트" {
        val manualNumbers =
            listOf(
                setOf(1, 2, 3, 4, 5, 6),
                setOf(7, 8, 9, 10, 11, 12),
            )
        val lottoTickets = generator.generateLottoTickets(manualNumbers)
        lottoTickets.size shouldBe 2
    }

    "수동 로또 티켓 생성 중복 실패 테스트" {
        val manualNumbers =
            listOf(
                setOf(1, 2, 3, 4, 5, 6),
                setOf(7, 8, 9, 10, 11, 12),
                setOf(1, 2, 3, 4, 5, 5),
            )
        shouldThrowExactly<IllegalArgumentException> {
            generator.generateLottoTickets(manualNumbers)
        }
    }

    "수동 로또 티켓 생성 범위 실패 테스트" {
        val manualNumbers =
            listOf(
                setOf(1, 2, 3, 4, 5, 6),
                setOf(7, 8, 9, 10, 11, 12),
                setOf(1, 2, 3, 4, 5, 46),
            )
        shouldThrowExactly<IllegalArgumentException> {
            generator.generateLottoTickets(manualNumbers)
        }
    }
})
