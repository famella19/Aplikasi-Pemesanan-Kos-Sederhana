package org.d3if3018.asessment1.ui.Artikel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3018.asessment1.R
import org.d3if3018.asessment1.model.Artikel
import org.d3if3018.asessment1.network.ArtikelApi

class ArtikelViewModel : ViewModel(){

    private val data = MutableLiveData<List<Artikel>>()

    init {
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch (Dispatchers.IO){
            try {
                data.postValue(ArtikelApi.service.getArtikel())
            }catch (e: Exception){
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
    fun getData(): LiveData<List<Artikel>> = data
}