package com.example.kshitij.pandemic

import com.example.kshitij.pandemic.repositories.CovidUpdateRepository
import com.example.kshitij.pandemic.ui.covidcases.CovidCasesViewModel
import com.example.kshitij.pandemic.ui.singlePerson.SinglePersonViewModel
import org.koin.dsl.module.module
import org.koin.android.viewmodel.ext.koin.viewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {
    single {
        CovidUpdateRepository(get())
    }
    single {
        createApi()
    }
    viewModel {
        CovidCasesViewModel(get())
    }
}

fun createApi() : NetworkApi{
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://api.covid19api.com")
        .build()
    return retrofit.create(NetworkApi::class.java)
}
