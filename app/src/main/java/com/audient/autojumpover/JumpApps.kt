package com.audient.autojumpover

data class JumpApp(
    val name: String,
    val packageName: String,
    val viewId: String
)

object JumpApps {
    val windowContentChangedAppMap: Map<String, JumpApp> by lazy {
        windowContentChangedAppList.associateBy { jumpApp: JumpApp -> jumpApp.packageName }
    }

    val windowStateChangedAppMap: Map<String, JumpApp> by lazy {
        windowStateChangedAppList.associateBy { jumpApp: JumpApp -> jumpApp.packageName }
    }

    private val windowContentChangedAppList: ArrayList<JumpApp> by lazy {
        arrayListOf(
            JumpApp("玩转地铁", "com.hskj.palmmetro", "com.hskj.palmmetro:id/tt_splash_skip_btn"),
            JumpApp("咪咕音乐", "cmccwm.mobilemusic", "cmccwm.mobilemusic:id/bt_skip_ad")
        )
    }

    private val windowStateChangedAppList: ArrayList<JumpApp> by lazy {
        arrayListOf(
            JumpApp(
                "喜马拉雅",
                "com.ximalaya.ting.android",
                "com.ximalaya.ting.android:id/host_count_down_click_lay"
            )
        )
    }
}
