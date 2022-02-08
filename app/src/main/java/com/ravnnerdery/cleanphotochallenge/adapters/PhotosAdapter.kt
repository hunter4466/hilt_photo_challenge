package com.ravnnerdery.cleanphotochallenge.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ravnnerdery.cleanphotochallenge.adapters.viewHolders.photoListViewHolder.*
import com.ravnnerdery.domain.models.PhotoInfo

class PhotosAdapter(private val clickListener: PhotoClickListener) :
    ListAdapter<PhotoInfo, PhotoListViewHolder>(PostListDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder {
        return if (viewType == 1) {
            SubViewHolderStraight.from(parent)
        } else {
            SubViewHolderReverse.from(parent)
        }
    }

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.id.toInt() % 2 == 0) {
            1
        } else {
            0
        }
    }
}
