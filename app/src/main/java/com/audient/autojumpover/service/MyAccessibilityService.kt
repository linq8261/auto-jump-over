@file:Suppress("NAME_SHADOWING")

package com.audient.autojumpover.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

private const val TAG = "MyAccessibilityService"

class MyAccessibilityService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.i(TAG, "onServiceConnected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val event = event ?: return

        Log.i(TAG, "${event.text} -> ${AccessibilityEvent.eventTypeToString(event.eventType)}")

        if (event.text.contains("喜马拉雅")) {
            val root = rootInActiveWindow
            findJumpNode(root)
        }

//        val source = event.source
//        source.performAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_LONG_CLICK.id)
    }

    private fun findJumpNode(node: AccessibilityNodeInfo) {
        Log.e(TAG, "${node.text}")

        if (!TextUtils.isEmpty(node.text) && node.text.contains("跳过")) {
            Log.e(TAG, "${node.className} ${node.text}")
            return
        }

        for (i in 0 until node.childCount) {
            val child = node.getChild(i) ?: continue
            findJumpNode(child)
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
