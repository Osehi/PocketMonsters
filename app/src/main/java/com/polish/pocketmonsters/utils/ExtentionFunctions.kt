package com.polish.pocketmonsters.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun getUrl(urlFromSource:String):String{

    val toGetNumber = urlFromSource.replace("/", "")
    // check length of the url from the source
    if (toGetNumber.length == 29){
        return (toGetNumber[toGetNumber.length - 1].toString())
    } else {
        val first = toGetNumber[toGetNumber.length - 2].toString()
        val second = toGetNumber[toGetNumber.length - 1].toString()
        return "$first$second"
    }
}

fun bindImage(imgUri:String, imgView:ImageView){
    Glide.with(imgView.context)
            .load(imgUri).into(imgView)
}