package com.farhanrv.uploadimagedemo.di

import com.farhanrv.uploadimagedemo.network.ApiService
import com.farhanrv.uploadimagedemo.network.AppRepository
import com.farhanrv.uploadimagedemo.network.api.NetworkDataSource
import com.farhanrv.uploadimagedemo.ui.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
}

val apiModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        // put server url using ip address because we have to use local API using node.js
        val retrofit = Retrofit.Builder()
//            .baseUrl("http://192.168.43.159/my/api/store-image-demo/") // wifi redmi note 4
            .baseUrl("http://10.5.17.11/my/api/store-image-demo/") // TODO : input ip address then save to BuildConfig, then restart app, load url from BuildConfig
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { NetworkDataSource(get()) }
    single { AppRepository(get()) }
}