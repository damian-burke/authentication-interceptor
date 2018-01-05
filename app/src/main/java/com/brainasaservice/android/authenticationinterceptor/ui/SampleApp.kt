package com.brainasaservice.android.authenticationinterceptor.ui

import android.app.Application
import com.brainasaservice.android.authenticationinterceptor.di.component.AppComponent
import com.brainasaservice.android.authenticationinterceptor.di.component.DaggerAppComponent
import com.brainasaservice.android.authenticationinterceptor.di.module.ApiModule
import com.brainasaservice.android.authenticationinterceptor.di.module.PresenterModule

class SampleApp: Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .apiModule(ApiModule())
            .presenterModule(PresenterModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        component.inject(this)
    }
}