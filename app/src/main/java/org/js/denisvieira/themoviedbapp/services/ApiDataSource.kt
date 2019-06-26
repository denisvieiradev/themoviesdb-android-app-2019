package org.js.denisvieira.themoviedbapp.services

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.js.denisvieira.themoviedbapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiDataSource {

    companion object {

        fun <S> createService(serviceClass: Class<S>): S {

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)

            httpClient.addInterceptor(CustomInterceptorRequest())
            httpClient.addInterceptor(loggingInterceptor)

            GsonBuilder().setDateFormat("yyyy-MM-dd").create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build()

            return retrofit.create(serviceClass)
        }

    }


}
