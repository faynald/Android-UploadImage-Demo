package com.farhanrv.uploadimagedemo.ui

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.farhanrv.uploadimagedemo.databinding.ImageItemBinding
import com.farhanrv.uploadimagedemo.domain.ImageList

class ImageListAdapter :
    ListAdapter<ImageList, ImageListAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder =
        ItemViewholder(ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ImageListAdapter.ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageList) = with(binding) {
            val px = dpToPx(itemView.context, 16f)
            val loading = CircularProgressDrawable(itemView.context)
            loading.strokeWidth = 5f
            loading.centerRadius = 30f
            loading.start()

            Glide.with(itemView.context)
                .load("http://192.168.43.159/my/api/store-image-demo/images/${item.name}")
                .transform(CenterCrop(), RoundedCorners(px.toInt()))
                .into(binding.img)

            tvId.text = item.id.toString()
            tvTitle.text = item.name // TODO : remove .jpg

            root.setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<ImageList>() {
    override fun areItemsTheSame(oldItem: ImageList, newItem: ImageList): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ImageList,
        newItem: ImageList
    ): Boolean {
        return oldItem == newItem
    }
}

fun dpToPx(context: Context, dp: Float): Float {
    return dp * context.resources.displayMetrics.density
}