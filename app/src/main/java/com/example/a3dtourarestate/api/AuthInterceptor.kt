package com.example.a3dtourarestate.api

import com.example.a3dtourarestate.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request?.newBuilder()
            ?.addHeader("user-key", BuildConfig.ZOMATO_API_KEY)
            ?.build()
        return chain.proceed(request)
    }

}