package com.brainasaservice.android.authenticationinterceptor.di.module

import com.brainasaservice.android.authenticationinterceptor.TokenInterceptor
import com.brainasaservice.android.authenticationinterceptor.api.SampleApi
import com.brainasaservice.android.authenticationinterceptor.ui.login.LoginContract
import com.brainasaservice.android.authenticationinterceptor.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
    class PresenterModule {
    @Provides
    @Singleton
    fun provideLoginPresenter(interceptor: TokenInterceptor, api: SampleApi): LoginContract.Presenter = LoginPresenter(interceptor, api)
}