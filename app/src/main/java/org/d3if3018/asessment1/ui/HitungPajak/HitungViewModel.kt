package org.d3if3018.asessment1.ui.HitungPajak

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3018.asessment1.db.PajakDao
import org.d3if3018.asessment1.db.PajakEntity
import org.d3if3018.asessment1.model.HasilPajak
import org.d3if3018.asessment1.model.hitungPajak

class HitungViewModel(private val db: PajakDao) : ViewModel() {

    private val hasilPajak = MutableLiveData<HasilPajak?>()

    fun hitungPajak(luasTanah: Double, nilaiJualTanah: Double, luasBangunan: Double, nilaiJualBangunan: Double){
        val dataPajak = PajakEntity(
            luasTanah = luasTanah,
            nilaiJualTanah = nilaiJualTanah,
            luasBangunan = luasBangunan,
            nilaiJualBangunan = nilaiJualBangunan
        )
        hasilPajak.value = dataPajak.hitungPajak()

//        val hasilNjkp = (((luasTanah * nilaiJualTanah) + (luasBangunan * nilaiJualBangunan))) * 0.2
//        val njkp = hasilNjkp / 1_000_000
//        val pajak = 0.005 * hasilNjkp
//        hasilPajak.value = HasilPajak(njkp, pajak)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataPajak)
            }
        }
    }

    fun getHasilHitung(): LiveData<HasilPajak?> = hasilPajak

}