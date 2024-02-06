package org.example.config

object LottoConfig {
    const val START_NUMBER = 1
    const val END_NUMBER = 45
    const val LOTTO_TICKET_SIZE = 6
    const val ERROR_MESSAGE_LOTTO_SIZE = "로또 번호는 6개여야 합니다."
    const val ERROR_MESSAGE_DUPLICATE = "로또 번호는 중복되지 않아야 합니다."
    const val ERROR_MESSAGE_NUMBER_RANGE = "로또 번호는 1부터 45 사이여야 합니다."
    const val MANUAL_GENERATOR_NOT_SUPPORTED = "수동 로또 생성기는 수동으로 번호를 입력해야 합니다"
    const val BLANK = ""
    const val AUTO_MODE = "A"
}
