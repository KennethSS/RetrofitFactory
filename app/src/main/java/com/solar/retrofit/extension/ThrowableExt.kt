package com.solar.retrofit.extension

import com.solar.retrofit.R
import retrofit2.HttpException

fun Throwable.error() {
    when(this) {
        is HttpException -> {
            when(code()) {
                400 -> toast(R.string.exception_http_error_400)
                401 -> toast(R.string.exception_http_error_401)
                403 -> toast(R.string.exception_http_error_403)
                404 -> toast(R.string.exception_http_error_404)
                500 -> toast(R.string.exception_http_error_500)
                503 -> toast(R.string.exception_http_error_503)
                504 -> toast(R.string.exception_http_error_504)
                else -> toast(R.string.exception_http_error_basic)
            }
        }

        else -> toast(R.string.exception_error_basic)
    }
}