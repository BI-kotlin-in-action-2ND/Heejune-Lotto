package org.example.domain.generator

import org.example.domain.LottoTicket

interface LottoGenerator {
    fun generate(): LottoTicket
}
