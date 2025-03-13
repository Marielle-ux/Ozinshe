package com.example.ozinshe.presentation.about

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
    }

    private var dataForVideo: MovieByIdResponse? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
        val token = SharedProvider(requireContext()).getToken()
        viewModel.getMovieById(token, args.movieId)
        binding.btnPlay.setOnClickListener {
            if (dataForVideo?.video != null) {
                val action =
                    AboutFragmentDirections.actionAboutFragmentToVideoFragment(
                        dataForVideo?.video?.link ?: ""
                    )
                findNavController().navigate(action)
            } else {
                val action =
                    AboutFragmentDirections.actionAboutFragmentToSeriesFragment(args.movieId)
                findNavController().navigate(action)

            }
        }

        binding.textBolimder.setOnClickListener {
            val action = AboutFragmentDirections.actionAboutFragmentToSeriesFragment(args.movieId)
            findNavController().navigate(action)
        }
        binding.btnNextAllMovie.setOnClickListener {
            val action = AboutFragmentDirections.actionAboutFragmentToSeriesFragment(args.movieId)
            findNavController().navigate(action)
        }
        viewModel.moviesByIdResponse.observe(viewLifecycleOwner) {
            dataForVideo = it
            Log.d("AAA", it.toString())
            val adapter = ImageAdapter()
            binding.rcViewScreenShots.adapter = adapter
            adapter.submitList(it.screenshots)
            adapter.setOnScreenShotClickListener(object : RcViewItemClickImageCallback {
                override fun onClick(link: String) {
                    val action = AboutFragmentDirections.actionAboutFragmentToImageFragment(link)
                    findNavController().navigate(action)
                }
            }
            )

            Glide.with(requireContext()).load(it.poster.link).into(binding.imgPoster)
            binding.run {
                textTvTittleMovie.text = it.name
                val year = it.createdDate.substring(0, 4)
                var genreInfoAbout  = it.genres.joinToString(separator = " · "){ "${it.name}"}

                if (it.video == null) {
                    binding.textTvAdditionalInfoYear.text = year+ " · "
                        binding.textTvAdditionalInfoGenre?.text =  genreInfoAbout + " · " + "${it.seasonCount} сезон, ${it.seriesCount} серия"
                } else {
                    binding.run {
                        textTvAdditionalInfoYear.text = year+ " · "
                        binding.textTvAdditionalInfoGenre?.text = genreInfoAbout
                        textTvBolimder.visibility = View.GONE
                        textBolimder.visibility = View.GONE
                        btnNextAllMovie.visibility = View.GONE
                    }
                }
                Log.d("AAA", "DESCRIPTION" + it.description.toString())

                btnBack.setOnClickListener {
                    requireActivity().onBackPressed()
                }

                if (it.favorite) {
                    favoriteState = true
                    btnIconFavorite.background =
                        resources.getDrawable(R.drawable.ic_favorite_selected)
                } else {
                    favoriteState = false
                    btnIconFavorite.background =
                       resources.getDrawable(R.drawable.ic_favorite_unselected)
                }

                textTvDescription.text = it.description
                textTvDirector.text = it.director
                textTvProducer.text = it.producer


                if (textTvDescription.lineCount == 1) {
                    btnMoreDescription.visibility = View.GONE
                    fadingEdgeLayoutDescription.setFadeSizes(0, 0, 0, 0)
                } else {
                    btnMoreDescription.setOnClickListener {
                        if (textTvDescription.maxLines >= 100) {
                            textTvDescription.maxLines = 7
                            btnMoreDescription.text = "Толығырақ"
                            fadingEdgeLayoutDescription.setFadeSizes(0, 0, 120, 0)
                        } else {
                            textTvDescription.maxLines = 1000
                            btnMoreDescription.text = "Жасыру"
                            fadingEdgeLayoutDescription.setFadeSizes(0, 0, 0, 0)
                        }
                    }
                }
            }
        }
        binding.btnFavorite.setOnClickListener {
            Log.d("AAA", "btnFavorite = $token - $favoriteState")
            if (!favoriteState) {
                Log.d("AAA", "btnFavorite - add")
                viewModel.addFavorite(token, MovieIdModel(args.movieId))
            } else {
                Log.d("AAA", "btnFavorite - delete")
                viewModel.deleteFavorite(token, MovieIdModel(args.movieId))
            }
        }
        viewModel.favoriteState.observe(viewLifecycleOwner) {
            if (it) {
                favoriteState = true
                Log.d("AAA", " addFavorite observe: ${it}")
                binding.btnIconFavorite.background =
                    ContextCompat.getDrawable(requireContext(),R.drawable.ic_favorite_selected)
            } else {
                favoriteState = false
                Log.d("AAA", "deleteFavorite observe: ${it}")
                binding.btnIconFavorite.background =
                    resources.getDrawable(R.drawable.ic_favorite_unselected)
            }
        }
        viewModel.errorResponse.observe(viewLifecycleOwner) {
            Log.d("AAA", it)
        }
    }
}
