package com.example.kshitij.pandemic.models.response


import com.google.gson.annotations.SerializedName

data class CountryDataItem(
    @SerializedName("Countries")
    val countries: List<Country>,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Global")
    val global: Global
)