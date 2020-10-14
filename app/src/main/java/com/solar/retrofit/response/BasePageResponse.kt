package com.solar.retrofit.response

import com.google.gson.annotations.SerializedName

data class BasePageResponse<T>(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("result") val result: T
)