package com.ravnnerdery.cleanphotochallenge.utils

import android.webkit.WebSettings
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ravnnerdery.cleanphotochallenge.R

fun glidify(url: String, binding: ImageView) {
    val uri = GlideUrl(
        url, LazyHeaders.Builder()
            .addHeader(
                "User-Agent",
                WebSettings.getDefaultUserAgent(binding.context)
            )
            .build()
    )
    Glide
        .with(binding.context)
        .load(uri)
        .placeholder(R.drawable.background_img)
        .transition(DrawableTransitionOptions.withCrossFade(150))
        .into(binding)
}