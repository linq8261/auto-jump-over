@file:Suppress("NAME_SHADOWING")

package com.audient.autojumpover.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.audient.autojumpover.JumpApps
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

                val key = "${event.packageName}/${event.className}"
                JumpApps.jumpByTextAppMap[key]?.let {
                    val node = rootInActiveWindow?.findAccessibilityNodeInfosByText(it.viewText)
                        ?.getOrNull(0) ?: run {
                        Log.i(TAG, "没有找到对应的控件[${it.packageName}][${it.className}]")
                        return
                    }
                    jump(node, it.name, it.packageName)
                }
            }

            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> {
                JumpApps.jumpByViewIdAppMap[event.packageName]?.let {
                    val node = rootInActiveWindow?.findAccessibilityNodeInfosByViewId(it.viewId)
                        ?.getOrNull(0) ?: run {
                        return
                    }
                    jump(node, it.name, it.packageName)
                }
            }

            else -> {
            }
        }
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
