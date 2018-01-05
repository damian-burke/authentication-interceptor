package com.brainasaservice.android.authenticationinterceptor.di.module

import com.brainasaservice.android.authenticationinterceptor.TokenInterceptor
import com.brainasaservice.android.authenticationinterceptor.api.SampleApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun providesAuthenticationInterceptor(): TokenInterceptor = TokenInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(tokenInterceptor)
        addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        baseUrl("https://catfact.ninja/")
        addConverterFactory(GsonConverterFactory.create(gson))
        client(client)
    }.build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideSampleApi(retrofit: Retrofit) = retrofit.create(SampleApi::class.java)
}