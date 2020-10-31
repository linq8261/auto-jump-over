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

    fun getJumpAppList(): ArrayList<JumpApp> {
        return ArrayList<JumpApp>().apply {
            addAll(windowStateChangedAppList)
            addAll(windowContentChangedAppList)
        }
    }

    private val windowStateChangedAppList: ArrayList<JumpApp> by lazy {
        arrayListOf(
            JumpApp(
                "喜马拉雅",
                "com.ximalaya.ting.android",
                "com.ximalaya.ting.android:id/host_count_down_click_lay"
//            ),
//            JumpApp(
//                "美团外卖",
//                "com.sankuai.meituan.takeoutnew",
//                "com.sankuai.meituan.takeoutnew:id/ll_skip"
            )
        )
    }

    private val windowContentChangedAppList: ArrayList<JumpApp> by lazy {
        arrayListOf(
            JumpApp(
                "玩转地铁",
                "com.hskj.palmmetro",
                "com.hskj.palmmetro:id/tt_splash_skip_btn"
            ),
            JumpApp(
                "咪咕音乐",
                "cmccwm.mobilemusic",
                "cmccwm.mobilemusic:id/bt_skip_ad"
            ),
            JumpApp(
                "今日头条",
                "com.ss.android.article.news",
                "com.ss.android.article.news:id/dpo"
            ),
            JumpApp(
                "动卡空间",
                "com.citiccard.mobilebank",
                "com.citiccard.mobilebank:id/btn_skip"
            ),
            JumpApp(
                "买单吧",
                "com.bankcomm.maidanba",
                "com.bankcomm.maidanba:id/ll_ad_skip"
            ),
            JumpApp(
                "掌上生活",
                "com.cmbchina.ccd.pluto.cmbActivity",
                "com.cmbchina.ccd.pluto.cmbActivity:id/img_cancel"
            ),
            JumpApp(
                "京东金融",
                "com.jd.jrapp",
                "com.jd.jrapp:id/btn_jump"
            ),
            JumpApp(
                "百度手机助手",
                "com.baidu.appsearch",
                "com.baidu.appsearch:id/launcher_skip_layout"
            ),
            JumpApp(
                "美团外卖",
                "com.sankuai.meituan.takeoutnew",
                "com.sankuai.meituan.takeoutnew:id/ll_skip"
            )
        )
    }
}
