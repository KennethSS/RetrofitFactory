package com.solar.retrofit.extension

import android.content.Context
import android.widget.Toast
import com.solar.retrofit.App

fun toast(msg: Any, length: Int = Toast.LENGTH_SHORT) {
    App.appContext.toast(msg, length)
}

fun Context.toast(msg: Any, length: Int = Toast.LENGTH_SHORT) {
    if(msg is String) toast(msg)
    else if (msg is Int) toast(getString(msg, length))
}