@file:Suppress("NAME_SHADOWING")

package com.audient.autojumpover.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.audient.autojumpover.utils.ToastUtils

private const val TAG = "MyAccessibilityService"

class MyAccessibilityService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.i(TAG, "onServiceConnected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        when (event?.eventType) {
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> {
//                Log.i(TAG, "TYPE_WINDOW_CONTENT_CHANGED -> ${event.packageName}/${event.className}")
                when (event.packageName) {
                    "com.ximalaya.ting.android" -> {
                        val app = "喜马拉雅"
                        val node =
                            rootInActiveWindow?.findAccessibilityNodeInfosByViewId("com.ximalaya.ting.android:id/host_count_down_click_lay")
                                ?.getOrNull(0) ?: run {
//                            LogUtils.d(TAG, "没有找到对应的控件[$app][${event.className}]")
                                return
                            }
                        jump(node, app)
                    }
                    "com.hskj.palmmetro" -> {
                        val app = "玩转地铁"
                        val node =
                            rootInActiveWindow?.findAccessibilityNodeInfosByViewId("com.hskj.palmmetro:id/tt_splash_skip_btn")
                                ?.getOrNull(0) ?: run {
//                            LogUtils.d(TAG, "没有找到对应的控件[$app][${event.className}]")
                                return
                            }
                        jump(node, app)
                    }
                    else -> {
                        val app = "未知应用"
                        val node = rootInActiveWindow?.findAccessibilityNodeInfosByText("跳过")
                            ?.getOrNull(0) ?: run {
                            return
                        }
                        jump(node, app)
                    }
                }
            }
        }
    }

    private fun jump(node: AccessibilityNodeInfo, app: String) {
        var success = node.performAction(AccessibilityNodeInfo.ACTION_CLICK)

        // 如果失败，尝试点击父控件
        if (!success) {
            success = node.parent?.performAction(AccessibilityNodeInfo.ACTION_CLICK) ?: false
        }

        ToastUtils.show("$app:自动跳过[$success]")
        Log.i(TAG, "$app:自动跳过[$success]")
    }

    private fun AccessibilityNodeInfo.traversal() {
        Log.i(TAG, "$className $text")
        for (index in 0 until childCount) {
            val child = getChild(index) ?: continue
            child.traversal()
        }
    }

    override fun onInterrupt() {
        Log.i(TAG, "onInterrupt")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "onUnbind")
        return super.onUnbind(intent)
    }
}
