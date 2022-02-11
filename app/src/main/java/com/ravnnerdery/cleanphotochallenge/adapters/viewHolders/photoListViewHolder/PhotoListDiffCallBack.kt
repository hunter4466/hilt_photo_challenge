package com.ravnnerdery.cleanphotochallenge.adapters.viewHolders.photoListViewHolder

import androidx.recyclerview.widget.DiffUtil
import com.ravnnerdery.domain.models.PhotoInfo

class PostListDiffCallBack : DiffUtil.ItemCallback<PhotoInfo>() {
    override fun areItemsTheSame(oldItem: PhotoInfo, newItem: PhotoInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoInfo, newItem: PhotoInfo): Boolean {
        return oldItem.id == newItem.id
    }
}