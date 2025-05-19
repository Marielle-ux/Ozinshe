package com.example.ozinshe

interface NavigationHostProvider {
    fun setNavigationVisibility(visible: Boolean)
    fun setStateBarVisibility(visible: Boolean)
    fun setNavigationToolBar(visible: Boolean, btnExitVisible: Boolean)
        fun additionalToolBarConfig(
            toolbarVisible: Boolean,
            btnExitVisible: Boolean,
            titleVisible: Boolean,
            title: String
        )
    }