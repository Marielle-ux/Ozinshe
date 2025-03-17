package com.example.ozinshe.presentation.profile

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch
import java.util.Locale

class ProfileFragment : Fragment(), OnLanguageSelectedListener {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        systemLanguage()

        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        if (Build.VERSION.SDK_INT >= 26) {
            transaction.setReorderingAllowed(false)
        }
        transaction.detach(this).attach(this).commit()
        viewModel.language.observe(viewLifecycleOwner) {
            binding.tvSelectedLanguage.text = it
        }

        binding.btnChangeLanguage.setOnClickListener {
            val bottomSheet = SelectedLanguageBottomSheet()
            bottomSheet.setOnLanguageListener(this)
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }
        binding.run {
            SwitchDayNight.isChecked = SharedProvider(requireContext()).getDayMode()
            SwitchDayNight.setOnCheckedChangeListener{_, isChecked ->
                SharedProvider(requireContext()).saveDayMode(isChecked)
                AppCompatDelegate.setDefaultNightMode(
                    if(isChecked) AppCompatDelegate.MODE_NIGHT_YES
                    else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }
    }

    override fun onLanguageSelected(language: String) {
        viewModel.selectLanguage(language)
    }

    private fun systemLanguage() {
        when (SharedProvider(requireContext()).getLanguage()) {
            "kk" -> {
                val local = Locale("kk")
                binding.tvSelectedLanguage.text = "Қазақша"
                val config = resources.configuration
                config.setLocale(local)
                requireContext().resources.updateConfiguration(
                    config,
                    requireContext().resources.displayMetrics
                )
            }

            "ru" -> {
                val local = Locale("ru")
                val config = resources.configuration
                config.setLocale(local)
                requireContext().resources.updateConfiguration(
                    config,
                    requireContext().resources.displayMetrics
                )
                binding.tvSelectedLanguage.text = "Русский"
            }

            "en" -> {
                val local = Locale("en")
                val config = resources.configuration
                config.setLocale(local)
                requireContext().resources.updateConfiguration(
                    config,
                    requireContext().resources.displayMetrics
                )
                binding.tvSelectedLanguage.text = "English"
            }

            else -> {
                val local = Locale("kk")
                Locale.setDefault(local)
                val config = resources.configuration
                config.setLocale(local)
                requireContext().resources.updateConfiguration(
                    config,
                    requireContext().resources.displayMetrics
                )
                binding.tvSelectedLanguage.text = "Қазақша"
            }
        }
    }
}
