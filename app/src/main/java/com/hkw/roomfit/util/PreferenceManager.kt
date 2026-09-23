package com.hkw.roomfit.util

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class PreferenceManager @Inject constructor(
    @ApplicationContext context : Context
){
    private val prefs: SharedPreferences =
        context.getSharedPreferences("roomfit_prefs", Context.MODE_PRIVATE)

    private val _PREFS_NAME_KEY = "user_name"

    fun getName(): String {
        val name: String = prefs.getString(_PREFS_NAME_KEY, "") ?: ""
//        return prefs.getString(key, "")
            return name
    }
    fun setName(value: String) {
        prefs.edit { putString(_PREFS_NAME_KEY, value) }
    }

    fun remove(key: String) {
        prefs.edit { remove(key) }
    }

    fun clear() {
        prefs.edit { clear() }
    }

}
