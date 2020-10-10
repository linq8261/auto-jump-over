package com.audient.autojumpover.utils

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.widget.Toast

/**
 * 最后修改时间：2018/8/28
 */

@SuppressLint("StaticFieldLeak")
object ToastUtils {
    private lateinit var applicationContext: Context
    private var mToast: Toast? = null

    fun init(context: Context) {
        applicationContext = context.applicationContext
    }

    fun show(resId: Int) {
        val text = applicationContext.getString(resId)
        show(text, Toast.LENGTH_SHORT)
    }

    fun show(text: String?) {
        show(text, Toast.LENGTH_SHORT)
    }

    @SuppressLint("ShowToast")
    fun show(text: String?, duration: Int) {
        if (TextUtils.isEmpty(text)) return

        if (mToast == null) {
            mToast = Toast.makeText(applicationContext, text, duration)
        } else {
            mToast?.setText(text)
            mToast?.duration = duration
        }
        mToast?.show()
    }
}
