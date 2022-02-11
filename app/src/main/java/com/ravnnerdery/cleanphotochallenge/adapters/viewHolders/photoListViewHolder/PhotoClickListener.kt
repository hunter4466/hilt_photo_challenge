package com.ravnnerdery.cleanphotochallenge.adapters.viewHolders.photoListViewHolder

import com.ravnnerdery.domain.models.PhotoInfo

class PhotoClickListener(val clickListener: (postId: Long) -> Unit) {
    fun onClick(photoEntity: PhotoInfo) = clickListener(photoEntity.id)
}