package com.example.ozinshe.presentation.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentFavoriteBinding
import com.example.ozinshe.presentation.about.AboutFragmentDirections

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterFavorite = FavoriteAdapter()
        binding.rcFavoriteFragment.adapter = adapterFavorite
        adapterFavorite.setOnFavoriteClickListener(object : RcViewItemClickFavoriteCallback {
            override fun onFavoriteClick(movie: Int) {
                val action = AboutFragmentDirections.actionGlobalAboutFragment(movie)
                findNavController().navigate(action)
            }
        })

        val token = SharedProvider(requireContext()).getToken()
        viewModel.getFavoriteList(token)

        viewModel.favoriteList.observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                adapterFavorite.submitList(list)
            } else {
                Log.d("FavoriteFragment", "Список избранного пуст")
            }
        }
    }
}
