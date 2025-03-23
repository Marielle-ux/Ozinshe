package com.example.ozinshe.presentation.search
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.data.model.SearchResponseModelItem
import com.example.ozinshe.databinding.ItemSearchResultBinding

class SearchMovieAdapter:RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<SearchResponseModelItem>() {
        override fun areItemsTheSame(
            oldItem: SearchResponseModelItem,
            newItem: SearchResponseModelItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchResponseModelItem,
            newItem: SearchResponseModelItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    fun submitList(searchList: List<SearchResponseModelItem>) {
        differ.submitList(searchList)
    }

    private var listenerClickAtItem: SearchItemClickCallback? = null

    fun setOnSearchClickListener(listener: SearchItemClickCallback) {
        this.listenerClickAtItem = listener
    }


    inner class SearchMovieViewHolder(private var binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(searchItem: SearchResponseModelItem) {
            Glide.with(itemView.context)
                .load(searchItem.poster.link)
                .into(binding.itemImage)


            binding.itemTitle.text = searchItem.name
            binding.YearInfo.text = searchItem.year.toString()
            binding.tvTextGenreName.text = searchItem.name
            binding.tvGenreInRussian.text = searchItem.name
            itemView.setOnClickListener {
                listenerClickAtItem?.onMovieClick(searchItem.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        return SearchMovieViewHolder(
            ItemSearchResultBinding.inflate(
                android.view.LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }
}