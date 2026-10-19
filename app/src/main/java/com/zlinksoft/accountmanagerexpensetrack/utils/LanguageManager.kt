package com.zlinksoft.accountmanagerexpensetrack.utils

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LanguageManager {
    data class Language(val code: String, val name: String, val nativeName: String)

    val supportedLanguages = listOf(
        Language("en", "English", "English"),
        Language("hi", "Hindi", "हिन्दी"),
        Language("bn", "Bengali", "বাংলা"),
        Language("es", "Spanish", "Español"),
        Language("ar", "Arabic", "العربية"),
        Language("ur", "Urdu", "اردو"),
        Language("fr", "French", "Français"),
        Language("de", "German", "Deutsch"),
        Language("zh", "Chinese", "中文")
    )

    fun setLanguage(context: Context, languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }

    fun getCurrentLanguage(context: Context): String {
        return context.resources.configuration.locales[0].language
    }
}