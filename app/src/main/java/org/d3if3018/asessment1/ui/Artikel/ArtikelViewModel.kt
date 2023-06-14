package org.d3if3018.asessment1.ui.Artikel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3018.asessment1.MainActivity
import org.d3if3018.asessment1.R
import org.d3if3018.asessment1.model.Artikel
import org.d3if3018.asessment1.network.ApiStatus
import org.d3if3018.asessment1.network.ArtikelApi
import org.d3if3018.asessment1.network.UpdateWorker
import java.util.concurrent.TimeUnit

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

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

}