package org.d3if3018.asessment1.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val hasilPajak = MutableLiveData<HasilPajak?>()

    fun hitungPajak(luasTanah: Double, nilaiJualTanah: Double, luasBangunan: Double, nilaiJualBangunan: Double){
        val hasilNjkp = (((luasTanah * nilaiJualTanah) + (luasBangunan * nilaiJualBangunan))) * 0.2
        val njkp = hasilNjkp / 1_000_000
        val pajak = 0.005 * hasilNjkp
        hasilPajak.value = HasilPajak(njkp, pajak)
    }

    fun getHasilHitung(): LiveData<HasilPajak?> = hasilPajak
}