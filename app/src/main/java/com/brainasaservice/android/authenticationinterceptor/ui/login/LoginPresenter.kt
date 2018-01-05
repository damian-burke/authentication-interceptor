package com.brainasaservice.android.authenticationinterceptor.ui.login

import android.util.Log
import com.brainasaservice.android.authenticationinterceptor.TokenInterceptor
import com.brainasaservice.android.authenticationinterceptor.api.SampleApi
import com.brainasaservice.android.authenticationinterceptor.model.CatFact
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val tokenInterceptor: TokenInterceptor,
    private val api: SampleApi
): LoginContract.Presenter {
    var view: LoginContract.View? = null

    override fun attach(view: LoginContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun getFactWithUser(user: String, password: String) {
        // pretend that we're logging in somewhere and receive a random user-token...
        val responseUserToken = UUID.randomUUID().toString()

        // we will now pass the user token to our token interceptor
        tokenInterceptor.userToken = responseUserToken

        // and make a request to the secured endpoint
        api.getSomethingAsUser().enqueue(object: Callback<CatFact> {
            override fun onFailure(call: Call<CatFact>?, t: Throwable?) {
                Log.e("LoginPresenter", "API request failed.")
            }

            override fun onResponse(call: Call<CatFact>?, response: Response<CatFact>?) {
                response?.body()?.let {
                    view?.displayCatFact(it)
                }
            }
        })
    }

    override fun getFactWithApp() {
        val appToken = "this-is-my-app-token"

        // we will now pass the user token to our token interceptor
        tokenInterceptor.appToken = appToken

        // and make a request to the secured endpoint
        api.getSomethingAsApp().enqueue(object: Callback<CatFact> {
            override fun onFailure(call: Call<CatFact>?, t: Throwable?) {
                Log.e("LoginPresenter", "API request failed.")
            }

            override fun onResponse(call: Call<CatFact>?, response: Response<CatFact>?) {
                response?.body()?.let {
                    view?.displayCatFact(it)
                }
            }
        })
    }
}