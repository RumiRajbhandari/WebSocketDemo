package rumi.com.websocketdemo

import android.app.Application
import com.facebook.stetho.Stetho

class WebSocketApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

}