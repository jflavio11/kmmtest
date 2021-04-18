package com.jflavio.kmmtest.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * AndroidApp
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  17/04/2021
 */
@HiltAndroidApp
class AndroidApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}