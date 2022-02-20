package com.refaat.themoviesdb.data.remoteSource

import com.refaat.themoviesdb.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object CustomOkHttpClient {
    fun getCustomOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val clientInterceptor = Interceptor { chain ->
            var request: Request = chain.request()
            val url: HttpUrl =
                request.url.newBuilder().addQueryParameter("apiKey", BuildConfig.API_KEY).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addNetworkInterceptor(clientInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .callTimeout(5, TimeUnit.SECONDS)
            .callTimeout(5, TimeUnit.SECONDS)
            .build()
    }
}