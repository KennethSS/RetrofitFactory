package com.solar.retrofit

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkClient {
    var CONNECTION_TIMEOUT = 10L
    var WRITE_TIMEOUT = 30L
    var READ_TIMEOUT = 30L

    var HOST = ""

    var IS_DEBUG = false

    inline fun <reified T>provideService(): T  {
        val retrofit = provideRetrofit(buildOkHttpInterceptor(IS_DEBUG), HOST)
        return retrofit.create(T::class.java)
    }

    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    fun buildOkHttpInterceptor(isDebug: Boolean): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

        if (isDebug) {
            httpClientBuilder
                .addNetworkInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
        }

        httpClientBuilder.addNetworkInterceptor { chain ->
            val request = chain.request()
            val builder: Request.Builder = request.newBuilder()
            chain.proceed(builder.build())
        }

        return httpClientBuilder.build()
    }
}