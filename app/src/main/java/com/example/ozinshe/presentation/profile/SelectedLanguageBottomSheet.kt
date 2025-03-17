package com.example.ozinshe.presentation.profile

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.BottomsheetSelectLanguageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale

class SelectedLanguageBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: BottomsheetSelectLanguageBinding
    private var languageSelectedListener: OnLanguageSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetSelectLanguageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    fun setOnLanguageListener(listener: OnLanguageSelectedListener) {
        languageSelectedListener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val defaultLanguage: String = SharedProvider(requireContext()).getLanguage()
        when (defaultLanguage) {
            "kk" -> {
                selectedIcon(false, true, false)
            }

            "en" -> {
                selectedIcon(true, false, false)
            }

            "ru" -> {
                selectedIcon(false, false, true)
            }
        }
        binding.apply {
            btnSelectEnglish.setOnClickListener {
                changeLanguage("English")
                selectedIcon(true, false, false)
            }
            btnSelectKazakh.setOnClickListener {
                changeLanguage("Қазақша")
                selectedIcon(false, true, false)
            }
            btnSelectRussian.setOnClickListener {
                changeLanguage("Русский")
                selectedIcon(false, false, true)
            }
        }
    }

    fun changeLanguage(language: String) {
        when (language) {
            "Қазақша" -> {
                systemLanguageChange("kk")
                languageSelectedListener?.onLanguageSelected("Қазақша")
            }
            "Русский" -> {
                systemLanguageChange("ru")
                languageSelectedListener?.onLanguageSelected("Русский")
            }
            "English" -> {
                systemLanguageChange("en")
                languageSelectedListener?.onLanguageSelected("English")
            }
        }
    }

    fun systemLanguageChange(codeLanguage: String) {
        SharedProvider(requireContext()).saveLanguage(codeLanguage)
        val local = Locale(codeLanguage)
        Locale.setDefault(local)
        val config = Configuration()
        config.setLocale(local)
        requireContext().resources.updateConfiguration(
            config,
            requireContext().resources.displayMetrics
        )
        findNavController().navigate(
            R.id.profileFragment,
            arguments, NavOptions.Builder().setPopUpTo(R.id.profileFragment, true).build()
        )
    }


    fun selectedIcon(iconEnglish: Boolean, iconKazakh: Boolean, iconRussian: Boolean) {
        binding.apply {
            if (iconEnglish) {
                IconBtnSelectedEnglish.visibility = View.VISIBLE
            } else {
                IconBtnSelectedEnglish.visibility = View.GONE
            }
            if (iconKazakh) {
                IconBtnSelectedKazakh.visibility = View.VISIBLE
            } else {
                IconBtnSelectedKazakh.visibility = View.GONE
            }
            if (iconRussian) {
                IconBtnSelectedRussian.visibility = View.VISIBLE
            } else {
                IconBtnSelectedRussian.visibility = View.GONE
            }
        }
    }
}