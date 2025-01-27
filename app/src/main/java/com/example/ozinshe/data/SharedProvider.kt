package com.example.ozinshe.data

import android.content.Context
import android.content.SharedPreferences

class SharedProvider(private val context: Context) {
    private var shared_token = "SAVE_TOKEN"

    private val preference: SharedPreferences
        get() = context.getSharedPreferences("APP_PREFERENCE", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        preference.edit().putString(shared_token, token).apply()
    }

    fun getToken(): String {
        return preference.getString(shared_token, "").toString()
    }

    fun clearShared() {
        preference.edit().remove(shared_token).apply()
    }
}