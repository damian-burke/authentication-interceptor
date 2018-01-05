package com.brainasaservice.android.authenticationinterceptor.di.component

import com.brainasaservice.android.authenticationinterceptor.di.module.ApiModule
import com.brainasaservice.android.authenticationinterceptor.di.module.PresenterModule
import com.brainasaservice.android.authenticationinterceptor.ui.SampleApp
import com.brainasaservice.android.authenticationinterceptor.ui.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiModule::class,
    PresenterModule::class
])
interface AppComponent {
    fun inject(app: SampleApp)

    fun inject(activity: LoginActivity)
}