package com.solar.retrofit.service

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.solar.retrofit.response.GetUserResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface BasicService {

    // GET
    @GET("search/users")
    suspend fun getUserList(
        @Header("Authorization") token: String?,
        @Query("q", encoded = true) query: String
    )

    @GET
    fun getFavoriteListByPath(
        @Url path: String)

    // PATCH
    /**
     * JsonObject().apply {
     *    addProperty("restock_alert", isRestockAlert)
     * }
     */
    @PATCH("api/v1/user/favorite/spot/{spot_id}/")
    fun toggleSpotRestock(@Path("spot_id") spot_id: Int, @Body body: JsonObject)


    // Post
    @Multipart
    @POST("api/feed/")
    fun postFeed(
        @Part("text") text: String,
        @Part requestBody: MultipartBody.Part): Observable<Any>

    @FormUrlEncoded
    @POST("api/administrator/v2/search/view/")
    fun postSampleUrl(
        @Field("search_category_item_id") category: Int,
        @Field("last_index") lastIndex: Int?
    ) { }

    @POST("/api/v1/user/favorite/bulk/")
    fun addDatabaseFavorites(@Body body: JsonArray)

    // Delete
    @DELETE("/api/v1/user/favorite/spot/{spot_id}/")
    fun deleteSpotFavorite(@Path("spot_id") spot_id: Int): Call<Void>
}