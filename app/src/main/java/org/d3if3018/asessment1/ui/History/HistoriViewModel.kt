package org.d3if3018.asessment1.ui.History

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3018.asessment1.db.PajakDao

class HistoriViewModel(
    private val db: PajakDao
): ViewModel() {
    val data = db.getLastPajak()

    fun hapusData() = viewModelScope.launch{
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}