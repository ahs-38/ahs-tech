package com.zlinksoft.accountmanagerexpensetrack.utils

import android.content.Context
import android.content.SharedPreferences

object PreferencesManager {
    private const val PREFS_NAME = "account_manager_prefs"
    private const val KEY_CURRENCY = "currency"
    private const val KEY_LANGUAGE = "language"
    private const val KEY_DARK_THEME = "dark_theme"
    private const val KEY_BIOMETRIC = "biometric_enabled"
    private const val KEY_LOCKED = "is_locked"
    private const val KEY_PIN = "pin_code"

    fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    var currency: String
        get() = ""
        set(value) {
            // This is a placeholder - actual prefs are accessed via getPrefs
        }

    fun getCurrency(context: Context): String {
        return getPrefs(context).getString(KEY_CURRENCY, "USD") ?: "USD"
    }

    fun setCurrency(context: Context, value: String) {
        getPrefs(context).edit().putString(KEY_CURRENCY, value).apply()
    }

    fun getLanguage(context: Context): String {
        return getPrefs(context).getString(KEY_LANGUAGE, "en") ?: "en"
    }

    fun setLanguage(context: Context, value: String) {
        getPrefs(context).edit().putString(KEY_LANGUAGE, value).apply()
    }

    fun isDarkTheme(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_DARK_THEME, false)
    }

    fun setDarkTheme(context: Context, value: Boolean) {
        getPrefs(context).edit().putBoolean(KEY_DARK_THEME, value).apply()
    }

    fun isBiometricEnabled(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_BIOMETRIC, false)
    }

    fun setBiometricEnabled(context: Context, value: Boolean) {
        getPrefs(context).edit().putBoolean(KEY_BIOMETRIC, value).apply()
    }

    fun isLocked(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_LOCKED, false)
    }

    fun setLocked(context: Context, value: Boolean) {
        getPrefs(context).edit().putBoolean(KEY_LOCKED, value).apply()
    }

    fun getPinCode(context: Context): String {
        return getPrefs(context).getString(KEY_PIN, "") ?: ""
    }

    fun setPinCode(context: Context, value: String) {
        getPrefs(context).edit().putString(KEY_PIN, value).apply()
    }
}