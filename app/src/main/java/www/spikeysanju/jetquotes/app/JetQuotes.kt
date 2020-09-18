package www.spikeysanju.jetquotes.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import www.spikeysanju.jetquotes.utils.isNight

class JetQuotes : Application(){
    override fun onCreate() {
        super.onCreate()

        // check for ui mode & set accordingly
        val mode = if (isNight()) {
            AppCompatDelegate.MODE_NIGHT_NO
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(mode)
    }
}