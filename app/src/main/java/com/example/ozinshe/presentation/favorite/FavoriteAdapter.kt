package com.example.ozinshe.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.data.model.FavoriteModelItem
import com.example.ozinshe.databinding.ItemFavoriteBinding

class FavoriteAdapter :
    ListAdapter<FavoriteModelItem, FavoriteAdapter.FavoriteViewHolder>(FavoriteDiffCallback()) {

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
        fun bind(movie: FavoriteModelItem) {
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

    class FavoriteDiffCallback : DiffUtil.ItemCallback<FavoriteModelItem>() {
        override fun areItemsTheSame(
            oldItem: FavoriteModelItem,
            newItem: FavoriteModelItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FavoriteModelItem,
            newItem: FavoriteModelItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
