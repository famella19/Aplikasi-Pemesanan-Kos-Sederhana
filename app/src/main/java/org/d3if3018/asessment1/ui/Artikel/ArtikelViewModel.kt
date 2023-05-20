package org.d3if3018.asessment1.ui.Artikel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3018.asessment1.R
import org.d3if3018.asessment1.model.Artikel

class ArtikelViewModel : ViewModel(){

    private val data = MutableLiveData<List<Artikel>>()

    init {
        data.value = initData()
    }
        private fun initData(): List<Artikel>{
            return listOf(
                Artikel("PBB", "Pajak Bumi dan Bangunan", R.drawable.pbbb),
                Artikel("PPH", "Pajak Penghasilan", R.drawable.penghasilan),
                Artikel("PPN", "Pajak Pertambahan Nilai", R.drawable.ppn),
                Artikel("Bea Materai", "Pajak Bea Materai", R.drawable.bm),
                Artikel("PPnBM", "Pajak Penjualan atas Barang Mewah", R.drawable.ppnbm),
                Artikel("PAD", "Pajak Daerah", R.drawable.pad),
            )
    }
    fun getData(): LiveData<List<Artikel>> = data
}