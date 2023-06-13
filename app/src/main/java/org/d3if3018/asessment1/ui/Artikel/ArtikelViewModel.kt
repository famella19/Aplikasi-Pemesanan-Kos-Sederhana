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
import org.d3if3018.asessment1.network.ApiStatus
import org.d3if3018.asessment1.network.ArtikelApi

class ArtikelViewModel : ViewModel(){

    private val data = MutableLiveData<List<Artikel>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch (Dispatchers.IO){
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(ArtikelApi.service.getArtikel())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("ArtikelViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }
    fun getData(): LiveData<List<Artikel>> = data
    fun getStatus(): LiveData<ApiStatus> = status
}