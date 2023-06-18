package com.example.guidecomposeapp.screen.home

import android.net.Uri
import android.os.Parcelable
import androidx.core.os.bundleOf
import com.example.guidecomposeapp.toParcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ok(
    val data : Int
) : Parcelable
fun main(){
    val objext = Ok(5)

        val x = Uri.encode(Gson().toJson(objext))
        val bundle = bundleOf(
            "data" to x
        )
        val xx = bundle.toParcelable<Ok>("data") ?: Ok(0)
        print(xx)

}