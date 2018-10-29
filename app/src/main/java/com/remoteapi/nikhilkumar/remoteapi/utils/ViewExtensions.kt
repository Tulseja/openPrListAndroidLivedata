package com.remoteapi.nikhilkumar.remoteapi.utils

import android.support.annotation.DrawableRes
import android.view.View
import android.widget.ImageView
import com.remoteapi.nikhilkumar.remoteapi.R
import com.squareup.picasso.Picasso


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ImageView.loadImage(uri: String?, @DrawableRes placeHolder: Int = R.drawable.ic_launcher_background) {
    Picasso.with(this.context).load(uri).placeholder(placeHolder).into(this)
}