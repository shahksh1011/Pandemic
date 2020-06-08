package com.example.kshitij.pandemic

import com.example.kshitij.pandemic.models.response.CountryDataItem
import retrofit2.Call
import retrofit2.http.GET

interface NetworkApi {
    @GET("/summary")
    fun getCovidUpdates(): Call<CountryDataItem>
}