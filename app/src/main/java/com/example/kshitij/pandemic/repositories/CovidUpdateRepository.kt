package com.example.kshitij.pandemic.repositories

import android.util.Log
import com.example.kshitij.pandemic.NetworkApi
import com.example.kshitij.pandemic.models.response.CountryDataItem
import retrofit2.Call
import retrofit2.Response

class CovidUpdateRepository(val networkApi: NetworkApi) {
    fun getCovidUpdates(onUpdateData: OnUpdateData) {
        networkApi.getCovidUpdates().enqueue(object : retrofit2.Callback<CountryDataItem> {
            override fun onFailure(call: Call<CountryDataItem>, t: Throwable) {
                onUpdateData.onFailure()
            }

            override fun onResponse(
                call: Call<CountryDataItem>,
                response: Response<CountryDataItem>
            ) {
                Log.d("Response", response.body().toString())
                onUpdateData.onSuccess(response.body() as CountryDataItem)
            }

        })
    }
    interface OnUpdateData {
        fun onSuccess(data:CountryDataItem)
        fun onFailure()
    }
}