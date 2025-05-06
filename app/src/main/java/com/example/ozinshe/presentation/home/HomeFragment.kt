package com.example.ozinshe.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
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
            setNavigationVisibility(true)
            setStateBarVisibility(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
            setStateBarVisibility(true)
        }
        val adapterMainMovie = MainMovieAdapter()
        binding.rcMainMovies.adapter = adapterMainMovie
        adapterMainMovie.setOnMovieClickListener(object : RcViewItemClickMainMoviesCallback{
            override fun onClick(movieId: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment(movieId)
                findNavController().navigate(action)
            }
        })
        val token = SharedProvider(requireContext()).getToken()
        viewModel.getMainMovies(token)


        viewModel.mainMoviesResponse.observe(viewLifecycleOwner) {
            adapterMainMovie.submitList(it)
        }

        viewModel.getMoviesByCategory(token)
        val adapterCategoriesFirst = MoviesByCategoryMainAdapter()
        val adaptCategoriesSecond = MoviesByCategoryMainAdapter()
        val adapterCategoriesThird = MoviesByCategoryMainAdapter()
        adapterCategoriesFirst.setOnMovieClickListener(object : RcViewItemClickMainMoviesCallback {
            override fun onClick(movieId: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment(movieId)
                findNavController().navigate(action)
            }
        })

        adaptCategoriesSecond.setOnMovieClickListener(object : RcViewItemClickMainMoviesCallback {
            override fun onClick(movieId: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment(movieId)
                findNavController().navigate(action)
            }
        })

        adapterCategoriesThird.setOnMovieClickListener(object : RcViewItemClickMainMoviesCallback {
            override fun onClick(movieId: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment(movieId)
                findNavController().navigate(action)
            }
        })

        viewModel.moviesByCategoryMainModel.observe(viewLifecycleOwner) {

            binding.rcFirstMovieCategory.adapter = adapterCategoriesFirst
            binding.tvFirstCategoryTitle.text = it[0].categoryName
            adapterCategoriesFirst.submitList(it[0].movies)

            binding.rcSecondMovieCategory.adapter = adaptCategoriesSecond
            binding.tvSecondCategoryTitle.text = it[1].categoryName
            adaptCategoriesSecond.submitList(it[1].movies)

            binding.rThirdMovieCategory.adapter = adapterCategoriesThird
            binding.tvThirdCategoryTitle.text = it[2].categoryName
            adapterCategoriesThird.submitList(it[2].movies)
        }
    }
}
