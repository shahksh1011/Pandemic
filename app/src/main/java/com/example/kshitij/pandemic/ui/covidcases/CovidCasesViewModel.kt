package com.example.kshitij.pandemic.ui.covidcases

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kshitij.pandemic.models.response.CountryDataItem
import com.example.kshitij.pandemic.repositories.CovidUpdateRepository
import org.koin.standalone.KoinComponent

class CovidCasesViewModel(val covidUpdateRepository: CovidUpdateRepository) : ViewModel(), KoinComponent {

    var listOfUpdates = MutableLiveData<CountryDataItem>()
    init{
    //listofUpdates
    }
    fun getAllData(){
        covidUpdateRepository.getCovidUpdates(object : CovidUpdateRepository.OnUpdateData{
            override fun onSuccess(data: CountryDataItem) {
                listOfUpdates.value = data
                Log.d("Data", data.toString())
            }

            override fun onFailure() {
                Log.d("APICALL", "FALED")
            }

        })
    }
}