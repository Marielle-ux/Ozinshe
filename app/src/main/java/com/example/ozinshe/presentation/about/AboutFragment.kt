@file:Suppress("NAME_SHADOWING")

package com.example.ozinshe.presentation.about

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.data.model.MovieByIdResponse
import com.example.ozinshe.data.model.MovieIdModel
import com.example.ozinshe.databinding.FragmentAboutBinding
import com.example.ozinshe.provideNavigationHost

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    private val args: AboutFragmentArgs by navArgs()
    private val viewModel: AboutViewModel by viewModels()
    private var favoriteState: Boolean = false
    private var dataForVideo: MovieByIdResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
            setStateBarVisibility(false)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
            setStateBarVisibility(false)
        }

        val token = SharedProvider(requireContext()).getToken()
        viewModel.getMovieById(token, args.movieId)

        // Навигация
        binding.btnPlay.setOnClickListener {
            dataForVideo?.let { movie ->
                val action = if (movie.video != null) {
                    AboutFragmentDirections.actionAboutFragmentToVideoFragment(movie.video.link)
                } else {
                    AboutFragmentDirections.actionAboutFragmentToSeriesFragment(args.movieId)
                }
                findNavController().navigate(action)
            }
        }

        val toSeriesAction = AboutFragmentDirections.actionAboutFragmentToSeriesFragment(args.movieId)
        binding.textBolimder.setOnClickListener { findNavController().navigate(toSeriesAction) }
        binding.btnNextAllMovie.setOnClickListener { findNavController().navigate(toSeriesAction) }

        // Наблюдение за данными фильма
        viewModel.moviesByIdResponse.observe(viewLifecycleOwner) { movie ->
            dataForVideo = movie
            Log.d("AAA", movie.toString())

            val adapter = ImageAdapter().apply {
                submitList(movie.screenshots)
                setOnScreenShotClickListener(object : RcViewItemClickImageCallback {
                    override fun onClick(link: String) {
                        val action = AboutFragmentDirections.actionAboutFragmentToImageFragment(link)
                        findNavController().navigate(action)
                    }
                })
            }
            binding.rcViewScreenShots.adapter = adapter

            Glide.with(requireContext()).load(movie.poster.link).into(binding.imgPoster)

            val year = movie.createdDate.substring(0, 4)
            val genreText = movie.genres.joinToString(" · ") { it.name }

            with(binding) {
                textTvTittleMovie.text = movie.name
                textTvAdditionalInfoYear.text = "$year · "

                if (movie.video == null) {
                    textTvAdditionalInfoGenre?.text =
                        "$genreText · ${movie.seasonCount} сезон, ${movie.seriesCount} серия"
                } else {
                    textTvAdditionalInfoGenre?.text = genreText
                    textTvBolimder.visibility = View.GONE
                    textBolimder.visibility = View.GONE
                    btnNextAllMovie.visibility = View.GONE
                }

                textTvDescription.text = movie.description
                textTvDirector.text = movie.director
                textTvProducer.text = movie.producer

                // Кнопка назад
                btnBack.setOnClickListener { requireActivity().onBackPressed() }

                // Кнопка "Толығырақ"
                if (textTvDescription.lineCount == 1) {
                    btnMoreDescription.visibility = View.GONE
                    fadingEdgeLayoutDescription.setFadeSizes(0, 0, 0, 0)
                } else {
                    btnMoreDescription.setOnClickListener {
                        val expanded = textTvDescription.maxLines >= 100
                        textTvDescription.maxLines = if (expanded) 7 else 1000
                        btnMoreDescription.text = if (expanded) "Толығырақ" else "Жасыру"
                        fadingEdgeLayoutDescription.setFadeSizes(0, 0, if (expanded) 120 else 0, 0)
                    }
                }

                // Обновление состояния избранного
                viewModel.favoriteState.value = movie.favorite
            }
        }

        // Кнопка избранного
        binding.btnFavorite.setOnClickListener {
            val token = SharedProvider(requireContext()).getToken()
            val movieIdModel = MovieIdModel(args.movieId)
            if (!favoriteState) {
                viewModel.addFavorite(token, movieIdModel)
            } else {
                viewModel.deleteFavorite(token, movieIdModel)
            }
        }

        // Обновление иконки избранного
        viewModel.favoriteState.observe(viewLifecycleOwner) {
            favoriteState = it
            updateFavoriteIcon(it)
            Log.d("AAA", "favoriteState updated: $it")
        }

        viewModel.errorResponse.observe(viewLifecycleOwner) {
            Log.d("AAA", "Error: $it")
        }
    }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
        val drawable = if (isFavorite) {
            R.drawable.ic_favorite_selected
        } else {
            R.drawable.ic_favorite_unselected
        }
        binding.btnIconFavorite.background = ContextCompat.getDrawable(requireContext(), drawable)
    }
}
