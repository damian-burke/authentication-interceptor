package com.brainasaservice.android.authenticationinterceptor

import com.brainasaservice.authenticationinterceptor.AuthenticationInterceptor

class TokenInterceptor: AuthenticationInterceptor(AUTHENTICATION_HEADER) {

    var userToken = ""

    var appToken = ""

    override fun getTokenByIdentifier(identifier: String?): String = when(identifier) {
        KEY_USER_TOKEN -> userToken
        KEY_APP_TOKEN -> appToken
        else -> ""
    }

    companion object {
        const val AUTHENTICATION_HEADER = "Authentication"

        const val KEY_USER_TOKEN = "User Token"
        const val KEY_APP_TOKEN = "App Token"

        // To use in headers
        const val AUTHENTICATION_USER = "$AUTHENTICATION_HEADER: $KEY_USER_TOKEN"
        const val AUTHENTICATION_APP = "$AUTHENTICATION_HEADER: $KEY_APP_TOKEN"
    }
}