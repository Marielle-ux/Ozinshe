package com.example.ozinshe.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentSearchBinding
import com.example.ozinshe.provideNavigationHost

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val args: SearchFragmentArgs by navArgs()
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
            setNavigationToolBar(true, btnExitVisible = false)
            additionalToolBarConfig(
                false,
                btnExitVisible = false,
                titleVisible = true,
                title = "Iздеу"
            )
        }
    }

    override fun onPause() {
        super.onPause()
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
            setNavigationToolBar(true, false)
            additionalToolBarConfig(
                false,
                btnExitVisible = false,
                titleVisible = true,
                title = "Тізім"
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Скрытие и отображение контейнеров в зависимости от состояния поиска
        binding.sanattarConstraintLayout.visibility = View.VISIBLE
        binding.searchResultConstraintLayout.visibility = View.GONE
        binding.btnRefreshEditText.visibility = View.GONE

        // Добавление слушателя на EditText для поиска
        binding.editTextSearchMovie.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val inputText = s.toString()
                val token = SharedProvider(requireContext()).getToken()
                viewModel.getSearchMovie(token, search = inputText, args.movieId)

                if (s.isNullOrEmpty()) {
                    binding.btnRefreshEditText.visibility = View.GONE
                    binding.sanattarConstraintLayout.visibility = View.VISIBLE
                    binding.searchResultConstraintLayout.visibility = View.GONE
                } else {
                    binding.btnRefreshEditText.visibility = View.VISIBLE
                    binding.sanattarConstraintLayout.visibility = View.GONE // <<< ВАЖНО!
                    binding.searchResultConstraintLayout.visibility = View.VISIBLE

                    binding.btnRefreshEditText.setOnClickListener {
                        binding.editTextSearchMovie.text?.clear()
                        binding.sanattarConstraintLayout.visibility = View.VISIBLE
                        binding.searchResultConstraintLayout.visibility = View.GONE
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        val searchAdapter = SearchMovieAdapter()
        binding.rcViewSearchFragment.adapter = searchAdapter
        searchAdapter.setOnSearchClickListener(object : SearchItemClickCallback {
            override fun onMovieClick(movieId: Int) {
                Log.d("SEARCH", "Clicked movieId: $movieId")
                val action = SearchFragmentDirections.actionSearchFragmentToAboutFragment(args.movieId)
                findNavController().navigate(action)
            }
        })
        viewModel.searchMovies.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }
    }
}

