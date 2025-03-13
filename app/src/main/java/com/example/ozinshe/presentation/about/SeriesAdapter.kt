package com.example.ozinshe.presentation.about

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.data.model.Video
import com.example.ozinshe.databinding.ItemSeriesBinding

class SeriesAdapter: RecyclerView.Adapter<SeriesAdapter.SeriesHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(
            oldItem: Video,
            newItem: Video
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Video,
            newItem: Video
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)
    fun submitList(list: List<Video>) {
        differ.submitList(list)
    }

    private var listenerClickAtItem: RcViewItemClickVideoCallback? = null

    fun setOnVideoClickListener(listener: RcViewItemClickVideoCallback) {
        this.listenerClickAtItem = listener
    }

    inner class SeriesHolder(private var binding: ItemSeriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(video: Video) {
            binding.apply {
                Glide.with(itemView)
                    .load("http://img.youtube.com/vi/${video.link}/maxresdefault.jpg")
                    .into(binding.imgTvSeries)
                textSeries.text = "${video.number}-ші бөлім"
                root.setOnClickListener {
                    listenerClickAtItem?.onVideoItemClick(video.link)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesHolder {
        return SeriesHolder(
            ItemSeriesBinding.inflate(
                android.view.LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SeriesHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }
}