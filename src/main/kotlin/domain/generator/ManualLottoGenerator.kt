package org.example.domain.generator

import org.example.domain.LottoTicket
import java.util.TreeSet

open class ManualLottoGenerator {
    fun generate(manualNumbers: TreeSet<Int>): LottoTicket {
        return LottoTicket(manualNumbers)
    }
}
