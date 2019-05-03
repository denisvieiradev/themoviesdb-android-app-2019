package org.js.denisvieira.themoviedbapp.services

import android.annotation.SuppressLint
import org.js.denisvieira.themoviedbapp.application.helper.handlerstatuscode.HandlerErrorStatusCode
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response
import org.js.denisvieira.themoviedbapp.BuildConfig
import java.io.IOException
import java.net.UnknownHostException
import java.util.*

class CustomInterceptorRequest : Interceptor {

    private val noInternetConnectionErrorCodeEnum = 600
    private val unProcessableEntityStatusCode     = 422

    companion object {
        @SuppressLint("ConstantLocale")
        val DEFAULT_LANGUAGE     = Locale.getDefault().language
        const val API_KEY        = BuildConfig.THE_MOVIE_DB_API_KEY
        const val DEFAULT_REGION = "BR"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        var response: Response? = null
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("language", DEFAULT_LANGUAGE)
                .addQueryParameter("region", DEFAULT_REGION)
                .build()

        val requestBuilder = original.newBuilder()
                .url(url)

        response = getResponse(response, chain, requestBuilder)
        validateStatusCode(response!!)

        return response
    }

    private fun getResponse(response: Response?, chain: Chain, requestBuilder: Request.Builder): Response? {
        var newResponse = response
        try {
            newResponse = chain.proceed(requestBuilder.build())
        } catch (ex: Exception) {
            when (ex) {
                is UnknownHostException -> {
                    handlerException(noInternetConnectionErrorCodeEnum)
                }
                else -> handlerException(0)
            }
        }
        return newResponse
    }

    private fun validateStatusCode(response: Response) {
        val successStatusCodeRange = 200..298
        val isStatusCodeRangeSuccess = response.code() in successStatusCodeRange
        val isNotStatusCodeRangeSuccess = !isStatusCodeRangeSuccess

        when {
            isNotStatusCodeRangeSuccess -> {
                if (response.code() != unProcessableEntityStatusCode) {
                    handlerException(response.code())
                }
            }
        }
    }

    private fun handlerException(statusCode: Int) {
        val messageFromStringResource = HandlerErrorStatusCode.fromInt(statusCode)
        throw Throwable(messageFromStringResource?.getMessageFromResourceString().toString())
    }

}
