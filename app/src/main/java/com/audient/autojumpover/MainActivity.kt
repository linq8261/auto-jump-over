package com.audient.autojumpover

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import java.util.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_jump_app_list.text = "匹配的应用列表：\n"
        val text = JumpApps.getJumpAppList().joinToString("\n") { it.name }
        tv_jump_app_list.append(text)
        tv_jump_app_list.append("\n\n匹配的关键字列表：\n")
        tv_jump_app_list.append(keywords.joinToString("\n"))
    }
}
