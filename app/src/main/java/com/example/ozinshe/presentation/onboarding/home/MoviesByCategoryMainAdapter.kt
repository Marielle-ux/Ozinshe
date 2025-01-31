package com.example.ozinshe.presentation.onboarding.home

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.data.model.Movy
import com.example.ozinshe.databinding.ItemCategoryMovieBinding

class MoviesByCategoryMainAdapter : RecyclerView.Adapter<MoviesByCategoryMainAdapter.MoviesByCategoryMainHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Movy>() {
        override fun areItemsTheSame(
            oldItem: Movy,
            newItem: Movy
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Movy,
            newItem: Movy
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)
    fun submitList(listMoviesMain:List<Movy>) {
        differ.submitList(listMoviesMain)
    }

    private var listenerClickAtItem: RcViewItemClickMainMoviesCallback? = null

    fun setOnMovieClickListener(listener: RcViewItemClickMainMoviesCallback) {
        this.listenerClickAtItem = listener
    }

    inner class MoviesByCategoryMainHolder(private var binding: ItemCategoryMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(movieItem: Movy) {
            Glide.with(itemView.context)
                .load(movieItem.poster.link)
                .into(binding.imgCategorySeries)


            binding.tvTitleFilm.text = movieItem.name
            binding.tvDescriptionFilm.text = movieItem.categoryAges[0].name
            itemView.setOnClickListener {
                listenerClickAtItem?.onClick(movieItem.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesByCategoryMainHolder {
        return MoviesByCategoryMainHolder(
            ItemCategoryMovieBinding.inflate(
                android.view.LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MoviesByCategoryMainHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }
}