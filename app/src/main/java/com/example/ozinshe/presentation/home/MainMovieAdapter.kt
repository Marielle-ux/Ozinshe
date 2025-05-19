package com.example.ozinshe.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.data.model.MainMovieResponseItem
import com.example.ozinshe.databinding.ItemMainMoviesBinding

class MainMovieAdapter : RecyclerView.Adapter<MainMovieAdapter.MainMovieHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<MainMovieResponseItem>() {
        override fun areItemsTheSame(
            oldItem: MainMovieResponseItem,
            newItem: MainMovieResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MainMovieResponseItem,
            newItem: MainMovieResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)
    fun submitList(listMoviesMain: List<MainMovieResponseItem>) {
        differ.submitList(listMoviesMain)
    }

    private var listenerClickAtItem: RcViewItemClickMainMoviesCallback? = null

    fun setOnMovieClickListener(listener: RcViewItemClickMainMoviesCallback) {
        this.listenerClickAtItem = listener
    }

    inner class MainMovieHolder(private var binding: ItemMainMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(mainMovieItem: MainMovieResponseItem) {
            Glide.with(itemView.context)
                .load(mainMovieItem.link)
                .into(binding.imgMainMovie)


            binding.tvTextTitle.text = mainMovieItem.movie.name
            binding.tvTextDescription.text = mainMovieItem.movie.description
            itemView.setOnClickListener {
                listenerClickAtItem?.onClick(mainMovieItem.movie.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMovieHolder {
        return MainMovieHolder(
            ItemMainMoviesBinding.inflate(
                android.view.LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MainMovieHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }
}
