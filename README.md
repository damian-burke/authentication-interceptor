# Authentication Interceptor

The authentication interceptor is a useful tool if your API contains multiple authentication scopes. 
It is based on the `Interceptor` provided by `OkHttp3` and works with temporary HTTP headers in the requests to 
determine which authentication should be used for certain HTTP calls.

## Requirements

The authentication interceptor itself is written in Java to avoid forcing the Kotlin dependency upon users. The 
example application is written in Kotlin.

The only external requirement of the authentication interceptor is `OkHttp`.

## Usage

To use the library simply implement the `AuthenticationInterceptor` abstract class. Upon instantiating it requires
a string which will be used as HTTP header for the authentication tokens.

In the example, the implementation looks as follows:

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
        }
    }
    
The `TokenInterceptor` keeps track of two tokens (`userToken` and `appToken`) which it will inject into the HTTP requests
depending on the existing `Authentication` HTTP header (possible values are `User Token` and `App Token` in this case).

This means these values will have to be populated before executing HTTP requests that require authentication.

As an example, during the login procedure the `userToken` can be set according to the API's login response.
If the user is already logged in and the token is stored in a database, it can be set at the application's start.

Adding the interceptor to your OkHttp client:

    OkHttpClient
      .Builder()
      .addInterceptor(tokenInterceptor)
      .build()

## Synergy with Retrofit 

In case you're using `Retrofit` to execute your HTTP requests, simply define header constants for each of your authentication
method within your implementation of `AuthenticationInterceptor`:

    const val AUTHENTICATION_USER = "$AUTHENTICATION_HEADER: $KEY_USER_TOKEN"
    const val AUTHENTICATION_APP = "$AUTHENTICATION_HEADER: $KEY_APP_TOKEN"

You can use these within your API interfaces:

    interface SampleApi {
        @GET("fact")
        @Headers(TokenInterceptor.AUTHENTICATION_APP)
        fun getSomethingAsApp(): Call<CatFact>

        @GET("fact")
        @Headers(TokenInterceptor.AUTHENTICATION_USER)
        fun getSomethingAsUser(): Call<CatFact>
    }

Make sure you're using the OkHttpClient with the added interceptor in your Retrofit instance and you're ready to go.

## Install
Gradle dependency:

    implementation 'com.brainasaservice:authentication-interceptor:1.0.0'

## License

This software is released under the [Apache License v2](https://www.apache.org/licenses/LICENSE-2.0). 
 
## Copyright

Copyright 2018 Damian Burke
