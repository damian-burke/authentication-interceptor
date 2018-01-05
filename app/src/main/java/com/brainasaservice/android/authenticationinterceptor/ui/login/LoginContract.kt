package com.brainasaservice.android.authenticationinterceptor.ui.login

import com.brainasaservice.android.authenticationinterceptor.model.CatFact

interface LoginContract {
    interface View {
        fun displayCatFact(fact: CatFact)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()

        fun getFactWithUser(user: String, password: String)
        fun getFactWithApp()
    }
}