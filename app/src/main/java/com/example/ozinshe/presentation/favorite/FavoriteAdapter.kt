package com.example.ozinshe.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.data.model.FavoriteModelItem
import com.example.ozinshe.data.model.favoriteModel.FavoriteListResponse
import com.example.ozinshe.data.model.favoriteModel.FavoriteListResponseItem
import com.example.ozinshe.databinding.ItemFavoriteBinding

class FavoriteAdapter : ListAdapter<FavoriteListResponseItem, FavoriteAdapter.FavoriteViewHolder>(FavoriteDiffCallback()) {
        private var list = mutableListOf<FavoriteListResponseItem>()

    private var onFavoriteClickListener: RcViewItemClickFavoriteCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    fun setOnFavoriteClickListener(listener: RcViewItemClickFavoriteCallback) {
        onFavoriteClickListener = listener
    }

    inner class FavoriteViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: FavoriteListResponseItem) {
            Glide.with(binding.root.context)
                .load(movie.poster.link)
                .into(binding.FavoriteItem)
            binding.FavoriteItemTitle.text = movie.name
            binding.FavoriteYearInfo.text = movie.year.toString()
            binding.FavoriteGenreName.text = movie.genres.joinToString(", ") { it.name }
            binding.root.setOnClickListener {
                onFavoriteClickListener?.onFavoriteClick(movie.id)
            }
        }
    }

    fun submitList(list: FavoriteListResponse) {
        this.list.clear()
        this.list.addAll(list.toList())
    }
    class FavoriteDiffCallback : DiffUtil.ItemCallback<FavoriteListResponseItem>() {
        override fun areItemsTheSame(
            oldItem: FavoriteListResponseItem,
            newItem: FavoriteListResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FavoriteListResponseItem,
            newItem: FavoriteListResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
