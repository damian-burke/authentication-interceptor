package com.brainasaservice.android.authenticationinterceptor.ui.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.brainasaservice.android.authenticationinterceptor.R
import com.brainasaservice.android.authenticationinterceptor.model.CatFact
import com.brainasaservice.android.authenticationinterceptor.ui.SampleApp
import kotlinx.android.synthetic.main.activity_main.buttonGetFact
import kotlinx.android.synthetic.main.activity_main.buttonGetFactAsApp
import kotlinx.android.synthetic.main.activity_main.textFact
import javax.inject.Inject

class LoginActivity: AppCompatActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as SampleApp).component.inject(this)
        presenter.attach(this)

        buttonGetFact.setOnClickListener {
            presenter.getFactWithUser("randomuser", "randompassword")
        }
        buttonGetFactAsApp.setOnClickListener {
            presenter.getFactWithApp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun displayCatFact(fact: CatFact) {
        textFact.text = fact.fact
    }
}