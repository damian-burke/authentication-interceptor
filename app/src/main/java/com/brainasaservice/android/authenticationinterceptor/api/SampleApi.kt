package com.brainasaservice.android.authenticationinterceptor.api

import com.brainasaservice.android.authenticationinterceptor.TokenInterceptor
import com.brainasaservice.android.authenticationinterceptor.model.CatFact
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface SampleApi {
    @GET("fact")
    @Headers(TokenInterceptor.AUTHENTICATION_APP)
    fun getSomethingAsApp(): Call<CatFact>

    @GET("fact")
    @Headers(TokenInterceptor.AUTHENTICATION_USER)
    fun getSomethingAsUser(): Call<CatFact>
}