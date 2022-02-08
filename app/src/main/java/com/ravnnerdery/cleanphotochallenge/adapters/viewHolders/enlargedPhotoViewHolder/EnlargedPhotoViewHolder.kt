package com.ravnnerdery.cleanphotochallenge.adapters.viewHolders.enlargedPhotoViewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravnnerdery.cleanphotochallenge.databinding.EnlargedPhotoBinding
import com.ravnnerdery.cleanphotochallenge.utils.glidify
import com.ravnnerdery.domain.models.PhotoInfo

class EnlargedPhotoViewHolder private constructor(private val binding: EnlargedPhotoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PhotoInfo) {
        binding.executePendingBindings()
        glidify(item.url, binding.enlargedPhotoView)
    }

    companion object {
        fun from(parent: ViewGroup): EnlargedPhotoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return EnlargedPhotoViewHolder(
                EnlargedPhotoBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
        }
    }
}