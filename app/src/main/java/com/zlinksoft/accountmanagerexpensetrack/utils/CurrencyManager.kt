package com.zlinksoft.accountmanagerexpensetrack.utils

import java.util.Locale

object CurrencyManager {
    data class Currency(val code: String, val symbol: String, val name: String)

    val supportedCurrencies = listOf(
        Currency("USD", "$", "US Dollar"),
        Currency("EUR", "€", "Euro"),
        Currency("GBP", "£", "British Pound"),
        Currency("INR", "₹", "Indian Rupee"),
        Currency("PKR", "₨", "Pakistani Rupee"),
        Currency("BDT", "৳", "Bangladeshi Taka"),
        Currency("SAR", "﷼", "Saudi Riyal"),
        Currency("AED", "د.إ", "UAE Dirham"),
        Currency("JPY", "¥", "Japanese Yen"),
        Currency("CNY", "¥", "Chinese Yuan"),
        Currency("CAD", "$", "Canadian Dollar"),
        Currency("AUD", "$", "Australian Dollar"),
        Currency("CHF", "Fr", "Swiss Franc")
    )

    fun getCurrency(code: String): Currency {
        return supportedCurrencies.firstOrNull { it.code == code } ?: supportedCurrencies.first()
    }

    fun getSymbol(code: String): String = getCurrency(code).symbol

    fun format(amount: Double, code: String = "USD"): String {
        val symbol = getSymbol(code)
        return "$symbol${String.format(Locale.US, "%,.2f", amount)}"
    }

    // Exchange rates relative to USD
    private val exchangeRates = mapOf(
        "USD" to 1.0, "EUR" to 0.92, "GBP" to 0.79, "INR" to 83.2, "PKR" to 278.0,
        "BDT" to 110.0, "SAR" to 3.75, "AED" to 3.67, "JPY" to 157.0, "CNY" to 7.25,
        "CAD" to 1.36, "AUD" to 1.51, "CHF" to 0.89
    )

    fun convert(amount: Double, from: String, to: String): Double {
        val fromRate = exchangeRates[from] ?: 1.0
        val toRate = exchangeRates[to] ?: 1.0
        return (amount / fromRate) * toRate
    }
}