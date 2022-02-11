package com.ravnnerdery.cleanphotochallenge.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ravnnerdery.domain.models.PhotoInfo

@BindingAdapter("capitalizeText")
fun TextView.setCapitalizedTitle(item: PhotoInfo?) {
    item?.let {
        (item.title[0].uppercaseChar() + item.title.substring(1)).also { text = it }
    }
}
