package com.solar.retrofit.view

import android.util.Log
import androidx.lifecycle.*
import com.solar.retrofit.NetworkClient
import com.solar.retrofit.response.UserItemResponse
import com.solar.retrofit.service.BasicService
import com.solar.retrofit.service.UserService
import kotlinx.coroutines.Dispatchers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.net.URI

class CoroutineViewModel : ViewModel() {
    private val service by lazy {
        NetworkClient.provideService<UserService>()
    }

    private val basicService by lazy {
        NetworkClient.provideService<BasicService>()
    }

    private val _loadList =  liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        try {
            val list = service.getUserList("kenneth").items.map { mapToUserView(it) }
            emit(list)
        } catch (e: Exception) {
            Log.d("CoroutineViewmodel", e.message?:"")
        }
    }
    val list: LiveData<List<UserView>> get() = _loadList


    fun getUser(q: String = "kenneth") {

    }

    fun postFeed(filePath: String) {
        val file = if (filePath.startsWith("files://")) {
            File(URI(filePath))
        } else {
            File(filePath)
        }

        val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body=  MultipartBody.Part.createFormData("image", file.name, requestBody)
        basicService.postFeed("Helllo", body)
    }

    private fun mapToUserView(type: UserItemResponse): UserView {
        return UserView(
            type.id ?: 0,
            type.login ?: "none",
            type.avatarUrl ?: "",
            type.url ?: ""
        )
    }
}