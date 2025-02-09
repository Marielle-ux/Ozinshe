package com.example.ozinshe.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentHomeBinding
import com.example.ozinshe.provideNavigationHost

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
        val token = SharedProvider(requireContext()).getToken()
        viewModel.getMainMovies(token)
        val adapterMainMovie = MainMovieAdapter()
        binding.rcMainMovies.adapter = adapterMainMovie
        viewModel.mainMoviesResponse.observe(viewLifecycleOwner) {
            adapterMainMovie.submitList(it)
        }

        viewModel.getMoviesByCategory(token)
        val adapterCategoriesFirst = MoviesByCategoryMainAdapter()
        val adaptCategoriesSecond = MoviesByCategoryMainAdapter()
        val adapterCategoriesThird = MoviesByCategoryMainAdapter()
        viewModel.moviesByCategoryMainModel.observe(viewLifecycleOwner) {
            binding.rcFirstMovieCategory.adapter = adapterCategoriesFirst
            binding.tvFirstCategoryTitle.text = it[0].categoryName
            adapterCategoriesFirst.submitList(it[0].movies)

//            binding.MovieCategory2.adapter = adaptCategoriesSecond
//            binding.tvRecFilms.text = it[1].categoryName
//            adaptCategoriesSecond.submitList(it[1].movies)
//
//            binding.MovieCategory3.adapter = adapterCategoriesThird
//            binding.rcCategories2.text = it[2].categoryName
//            adapterCategoriesThird.submitList(it[2].movies)
        }
    }
}
