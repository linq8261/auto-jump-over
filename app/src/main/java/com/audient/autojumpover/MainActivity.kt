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

//    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 需要手机中有语音引擎
        // 如果没有，可以安装 科大讯飞语音引擎3.0.apk 测试
        // 一般在无障碍设置中，文字转语音输出，选择引擎
//        tts = TextToSpeech(this,
//            TextToSpeech.OnInitListener { status ->
//                Log.i(TAG, "onInit status=$status")
//
//                if (status == TextToSpeech.SUCCESS) {
//                    val result = tts.setLanguage(Locale.ENGLISH)
//                    if (result == TextToSpeech.LANG_MISSING_DATA
//                        || result == TextToSpeech.LANG_NOT_SUPPORTED
//                    ) {
//                        Toast.makeText(this, "不支持此语言", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        )

//        btn_test.setOnClickListener {
//            if (!tts.isSpeaking) {
//                tts.speak("hello 中文", TextToSpeech.QUEUE_FLUSH, null)
//            }
//        }

//        btn_test_accessibility.setOnClickListener {
//            Log.e(TAG, "nothing")
//        }

        btn_test_accessibility.setOnLongClickListener {
            Toast.makeText(this, "无障碍自动触发的长按事件", Toast.LENGTH_LONG).show()
            true
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        tts.stop()
//        tts.shutdown()
//    }
}
