package com.example.ozinshe.presentation

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.ozinshe.NavigationHostProvider
import com.example.ozinshe.R
import com.example.ozinshe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationHostProvider {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding?.bottomNavBar?.itemIconTintList = null
        val navController = findNavController(R.id.nav_host_fragment)
        binding?.bottomNavBar?.setupWithNavController(navController)



        // Устанавливаем белый статус бар по умолчанию
        setStatusBarColor(isTransparent = false, isLight = true)
    }

    override fun setNavigationVisibility(visible: Boolean) {
        binding?.bottomNavBar?.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun setStateBarVisibility(visible: Boolean) {
        if (visible) {
            showStatusBar()
        } else {
            showTransparentStatusBar()
        }
    }

    override fun setNavigationToolBar(visible: Boolean, btnExitVisible: Boolean) {
    }

    override fun additionalToolBarConfig(
        toolbarVisible: Boolean,
        btnExitVisible: Boolean,
        titleVisible: Boolean,
        title: String
    ) {
    }
//TODO надо менять все что внизу ↓↓↓↓↓↓↓↓↓ чтобы статус бар был прозрачным и не прозрачным если не получится ок

    private fun showStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.show(WindowInsets.Type.statusBars())
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        setStatusBarColor(false, true) // не прозрачный, светлый

    }

    private fun showTransparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.show(WindowInsets.Type.statusBars())
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.statusBarColor = resources.getColor(android.R.color.transparent, theme)
    }

    private fun setStatusBarColor(isTransparent: Boolean, isLight:Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.show(WindowInsets.Type.statusBars())
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.statusBarColor = if (isTransparent) {
            Color.TRANSPARENT
        } else {
            ContextCompat.getColor(this, android.R.color.white)
        }
        // Настройка текста и иконок (светлый/тёмный)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                if (isLight) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = if (isLight) {
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                0
            }
        }
    }
}