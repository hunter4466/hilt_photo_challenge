package com.ravnnerdery.cleanphotochallenge.adapters

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.ListAdapter
import com.ravnnerdery.cleanphotochallenge.adapters.viewHolders.photoListViewHolder.*
import com.ravnnerdery.domain.models.PhotoInfo

class PhotosAdapter(private val clickListener: PhotoClickListener) :
    ListAdapter<PhotoInfo, PhotoListViewHolder>(PostListDiffCallBack()) {

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder {
        return if (viewType == 1) {
            SubViewHolderStraight.from(parent)
        } else {
            SubViewHolderReverse.from(parent)
        }
    }

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(itemView: View, position: Int) {
        if(position > lastPosition){
            var animation: Animation
            if (position % 2 == 0){
                animation = AnimationUtils.loadAnimation(itemView.context, com.ravnnerdery.cleanphotochallenge.R.anim.slide_in_left)
            } else {
                animation = AnimationUtils.loadAnimation(itemView.context, com.ravnnerdery.cleanphotochallenge.R.anim.slide_in_right)
            }
            itemView.startAnimation(animation)
            lastPosition = position
        }
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
