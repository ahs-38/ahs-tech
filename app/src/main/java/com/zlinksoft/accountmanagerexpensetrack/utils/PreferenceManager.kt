package com.zlinksoft.accountmanagerexpensetrack.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("account_manager_prefs", Context.MODE_PRIVATE)

    var currency: String
        get() = prefs.getString(KEY_CURRENCY, "USD") ?: "USD"
        set(value) = prefs.edit().putString(KEY_CURRENCY, value).apply()

    var language: String
        get() = prefs.getString(KEY_LANGUAGE, "en") ?: "en"
        set(value) = prefs.edit().putString(KEY_LANGUAGE, value).apply()

    var isDarkTheme: Boolean
        get() = prefs.getBoolean(KEY_DARK_THEME, false)
        set(value) = prefs.edit().putBoolean(KEY_DARK_THEME, value).apply()

    var isLocked: Boolean
        get() = prefs.getBoolean(KEY_LOCKED, false)
        set(value) = prefs.edit().putBoolean(KEY_LOCKED, value).apply()

    var pinCode: String
        get() = prefs.getString(KEY_PIN, "") ?: ""
        set(value) = prefs.edit().putString(KEY_PIN, value).apply()

    companion object {
        private const val KEY_CURRENCY = "currency"
        private const val KEY_LANGUAGE = "language"
        private const val KEY_DARK_THEME = "dark_theme"
        private const val KEY_BIOMETRIC = "biometric_enabled"
        private const val KEY_LOCKED = "is_locked"
        private const val KEY_PIN = "pin_code"
    }
}