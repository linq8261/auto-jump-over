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

        val text = JumpApps.getJumpAppList().joinToString("\n") { it.name }
        tv_jump_app_list.text = text
    }
}
