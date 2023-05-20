package org.d3if3018.asessment1.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pajak")
data class PajakEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var luasTanah: Double,
    var nilaiJualTanah: Double,
    var luasBangunan: Double,
    var nilaiJualBangunan: Double
)
