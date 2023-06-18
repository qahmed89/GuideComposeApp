package com.example.guidecomposeapp.helper

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import com.google.gson.Gson

inline fun <reified T> Bundle.toParcelable(key: String): T? {
    return Gson().fromJson(getString(key), T::class.java)
}

fun Parcelable.asBundle(): String? {
    return Uri.encode(Gson().toJson(this))
}