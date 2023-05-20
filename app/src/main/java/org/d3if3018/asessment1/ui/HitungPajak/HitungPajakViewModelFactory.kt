package org.d3if3018.asessment1.ui.HitungPajak

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3018.asessment1.db.PajakDao
import org.d3if3018.asessment1.ui.History.HistoriViewModel

class HitungPajakViewModelFactory (
    private val db: PajakDao ): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HitungViewModel::class.java)) {
            return HitungViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}