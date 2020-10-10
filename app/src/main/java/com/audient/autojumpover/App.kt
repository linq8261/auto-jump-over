package com.audient.autojumpover

import android.app.Application
import com.audient.autojumpover.utils.ToastUtils

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        ToastUtils.init(this)
    }
}