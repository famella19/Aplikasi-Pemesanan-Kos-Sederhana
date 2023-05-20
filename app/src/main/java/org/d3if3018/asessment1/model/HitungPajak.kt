package org.d3if3018.asessment1.model

import org.d3if3018.asessment1.db.PajakEntity

fun PajakEntity.hitungPajak(): HasilPajak{

    val hasilNjkp = (((luasTanah * nilaiJualTanah) + (luasBangunan * nilaiJualBangunan))) * 0.2
    val njkp = hasilNjkp / 1_000_000
    val pajak = 0.005 * hasilNjkp
    return HasilPajak(njkp, pajak)
}