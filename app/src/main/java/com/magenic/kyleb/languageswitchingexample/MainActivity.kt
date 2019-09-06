package com.magenic.kyleb.languageswitchingexample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val LANGUAGE_KEY = "language"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeTextView = findViewById<TextView>(R.id.timeTextView)
        timeTextView.text = SimpleDateFormat("h:mm a", Locale.getDefault()).format(Calendar.getInstance().time)
    }

    override fun attachBaseContext(newBase: Context?) {
        if(newBase != null) {
            val language = LSEApplication.preferences.getString(LANGUAGE_KEY, Locale.ENGLISH.language)!!
            super.attachBaseContext(LanguageContextWrapper.wrap(newBase, language))
        }
        else {
            super.attachBaseContext(newBase)
        }
    }

    fun onEnglishClick(view: View) {
        with(LSEApplication.preferences.edit()) {
            putString(LANGUAGE_KEY, Locale.ENGLISH.language)
            apply()
        }

        recreate()
    }

    fun onMandarinClick(view: View) {
        with(LSEApplication.preferences.edit()) {
            putString(LANGUAGE_KEY, Locale.CHINESE.language)
            apply()
        }

        recreate()
    }
}
