package com.solar.retrofit.view

import com.solar.recyclerview.ItemType
import com.solar.retrofit.R

data class UserView(
    val id: Int,
    val username: String,
    val profileImg: String,
    val url: String,
    var isFavorite: Boolean = false,
    override val layoutRes: Int = R.layout.item_user
) : ItemType