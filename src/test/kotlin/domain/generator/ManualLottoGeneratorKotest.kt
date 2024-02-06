package domain.generator

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.example.domain.generator.ManualLottoGenerator

class ManualLottoGeneratorKotest : StringSpec({
    val generator = ManualLottoGenerator()

    "수동 로또 번호 생성 테스트" {
        val validNumbers = listOf(listOf(1, 2, 3, 4, 5, 6))
        val lottoTicket = generator.generate(validNumbers)
        lottoTicket.get(0).numbers shouldBe validNumbers[0]
    }

    "수동 로또 번호 생성 중복 실패 테스트" {
        val invalidNumbers = listOf(listOf(1, 2, 3, 4, 5, 5))
        shouldThrowExactly<IllegalArgumentException> {
            generator.generate(invalidNumbers)
        }
    }

    "수동 로또 번호 생성 범위 실패 테스트" {
        val outOfRangeNumbers = listOf(listOf(0, 2, 3, 4, 5, 46))
        shouldThrowExactly<IllegalArgumentException> {
            generator.generate(outOfRangeNumbers)
        }
    }

    "수동 로또 번호 생성 갯수 실패 테스트" {
        val lessThanSixNumbers = listOf(listOf(1, 2, 3, 4, 5))
        shouldThrowExactly<IllegalArgumentException> {
            generator.generate(lessThanSixNumbers)
        }
    }
})
