package com.magenic.kyleb.languageswitchingexample

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import java.util.*

class LanguageContextWrapper(base: Context) : ContextWrapper(base) {
    companion object {
        fun wrap(context: Context, language: String) : ContextWrapper {
            val config = context.resources.configuration

            if(language != "" && getSystemLocale(config).language != language) {
                val locale = Locale(language)
                Locale.setDefault(locale)

                setSystemLocale(config, locale)
            }

            return LanguageContextWrapper(context.createConfigurationContext(config))
        }

        private fun getSystemLocale(config: Configuration) : Locale {
            return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.locales.get(0)
            }
            else {
                @Suppress("DEPRECATION")
                config.locale
            }
        }

        private fun setSystemLocale(config: Configuration, locale: Locale) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale)
            }
            else {
                @Suppress("DEPRECATION")
                config.locale = locale
            }
        }
    }
}