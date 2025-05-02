package com.shawn.githubdemo.model.retrofitManager

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.shawn.githubdemo.model.data.UrlManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitManager {
        private var retrofit: Retrofit
    private var apiService : APIService
    init{
        val httpClientBuilder = OkHttpClient.Builder().apply{
            connectTimeout(60,TimeUnit.SECONDS)
            readTimeout(60,TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
        }

        val baseUrl = UrlManager.BASE_URL
        val gson = GsonBuilder()
//            .registerTypeAdapter(Images::class.java, ImagesDeserializer())
//            .registerTypeAdapter(Facilities::class.java, FacilitiesDeserializer())
            .setLenient()
            .create()
        val httpClient = httpClientBuilder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        apiService = retrofit.create(APIService::class.java)


    }

    @Singleton
    @Provides
    fun getApiService():APIService{
        return apiService
    }
}