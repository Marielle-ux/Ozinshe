package com.example.ozinshe.presentation.about

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.data.model.ScreenshotXXX
import com.example.ozinshe.databinding.ItemScreenshotsBinding

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<ScreenshotXXX>() {
        override fun areItemsTheSame(
            oldItem: ScreenshotXXX,
            newItem: ScreenshotXXX
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ScreenshotXXX,
            newItem: ScreenshotXXX
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)
    fun submitList(list: List<ScreenshotXXX>) {
        differ.submitList(list)
    }

    private var listenerClickAtItem: RcViewItemClickImageCallback? = null

    fun setOnScreenShotClickListener(listener: RcViewItemClickImageCallback) {
        this.listenerClickAtItem = listener
    }

    inner class ImageHolder(private var binding: ItemScreenshotsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(screenShotItem: ScreenshotXXX) {
            Glide.with(itemView.context)
                .load(screenShotItem.link)
                .into(binding.imgScreenShots)


            itemView.setOnClickListener {
                listenerClickAtItem?.onClick(screenShotItem.link)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(
            ItemScreenshotsBinding.inflate(
                android.view.LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }
}