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

        // TODO: 2020/10/10 需不需要排除应用的功能：列出所有应用，让用户选择
        // 排除的应用
//                val excludePackages = arrayOf("com.audient.autojumpover")
//                if (excludePackages.contains(event.packageName)) return

        when (event?.eventType) {
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                Log.i(TAG, "TYPE_WINDOW_STATE_CHANGED -> ${event.packageName}/${event.className}")
                when (event.packageName) {
                    "com.ximalaya.ting.android" -> {
                        findNodeByViewIdAndJump(
                            appName = "喜马拉雅",
                            viewId = "com.ximalaya.ting.android:id/host_count_down_click_lay",
                            packageName = event.packageName.toString(),
                            className = event.className.toString()
                        )
                    }
                    // TODO: 2020/10/10 不要这个了，各种误触发
//                    else -> {
//                        val app = "未知应用"
//                        val node = rootInActiveWindow?.findAccessibilityNodeInfosByText("跳过")
//                            ?.getOrNull(0) ?: run {
//                            return
//                        }
//                        jump(node, app, event.packageName.toString())
//                    }
                }
            }
            // 一般来说用TYPE_WINDOW_STATE_CHANGED就能处理了，但是有些处理不了，需要用TYPE_WINDOW_CONTENT_CHANGED
            // TYPE_WINDOW_CONTENT_CHANGED是界面上每个控件变化都会回调，所以会比较频繁
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> {
//                Log.i(TAG, "TYPE_WINDOW_CONTENT_CHANGED -> ${event.packageName}/${event.className}")
                when (event.packageName) {
                    "com.hskj.palmmetro" -> {
                        findNodeByViewIdAndJump(
                            appName = "玩转地铁",
                            viewId = "com.hskj.palmmetro:id/tt_splash_skip_btn",
                            packageName = event.packageName.toString(),
                            className = event.className.toString()
                        )
                    }
                    "cmccwm.mobilemusic" -> {
                        findNodeByViewIdAndJump(
                            appName = "咪咕音乐",
                            viewId = "cmccwm.mobilemusic:id/bt_skip_ad",
                            packageName = event.packageName.toString(),
                            className = event.className.toString()
                        )
                    }
                }
            }
        }
    }

    private fun findNodeByViewIdAndJump(
        appName: String,
        viewId: String,
        packageName: String,
        className: String
    ) {
        val node = rootInActiveWindow?.findAccessibilityNodeInfosByViewId(viewId)
            ?.getOrNull(0) ?: run {
//            Log.i(TAG, "没有找到对应的控件[$appName][${className}]")
            return
        }
        jump(node, appName, packageName.toString())
    }

    private fun jump(node: AccessibilityNodeInfo, app: String, packageName: String) {
        var success = node.performAction(AccessibilityNodeInfo.ACTION_CLICK)

        // 如果失败，尝试点击父控件
        if (!success) {
            success = node.parent?.performAction(AccessibilityNodeInfo.ACTION_CLICK) ?: false
        }

        ToastUtils.show("$app:自动跳过[$success]")
        Log.i(TAG, "$app:自动跳过[$success][$packageName]")
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
