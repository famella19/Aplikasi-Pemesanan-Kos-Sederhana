package org.d3if3018.asessment1.ui.History

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3018.asessment1.db.PajakDao

class HistoriViewModelFactory (
    private val db: PajakDao
        ): ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoriViewModel::class.java)){
            return HistoriViewModel(db) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
    }
}