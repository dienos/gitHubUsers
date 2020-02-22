package com.jth.github.user.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("img_url")
fun setUserImg(view: ImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}


