package com.audient.autojumpover

// 当没有匹配到已经适配的app时，尝试寻找关键字
// 关键字要尽量避免误判，比如有些正常页面含有跳过二字的按钮
val keywords by lazy {
    arrayOf("跳过广告", "跳过 3", "跳过 2", "3 跳过", "2 跳过", "Skip 3", "Skip 2")
}

data class JumpApp(
    val name: String,
    val packageName: String,
    // 跳过按钮的viewId
    val viewId: String = "",
    // 跳过按钮的文本
    val viewText: String = "",
    // 跳过按钮所在的Activity的类名
    val className: String = ""
)

object JumpApps {
    val jumpByViewIdAppMap: Map<String, JumpApp> by lazy {
        jumpByViewIdAppList.associateBy { jumpApp: JumpApp -> jumpApp.packageName }
    }

    val jumpByTextAppMap: Map<String, JumpApp> by lazy {
        jumpByTextAppList.associateBy { jumpApp: JumpApp -> "${jumpApp.packageName}/${jumpApp.className}" }
    }

    fun getJumpAppList(): ArrayList<JumpApp> {
        return ArrayList<JumpApp>().apply {
            addAll(jumpByTextAppList)
            addAll(jumpByViewIdAppList)
        }
    }

    private val jumpByTextAppList: ArrayList<JumpApp> by lazy {
        arrayListOf(
            JumpApp(
                name = "发现精彩",
                packageName = "com.cs_credit_bank",
                viewText = "跳过",
                className = "com.mapass.example.activity.MainActivity_"
            )
        )
    }

    private val jumpByViewIdAppList: ArrayList<JumpApp> by lazy {
        arrayListOf(
            JumpApp(
                name = "喜马拉雅",
                packageName = "com.ximalaya.ting.android",
                viewId = "com.ximalaya.ting.android:id/host_count_down_click_lay"
            ),
            JumpApp(
                name = "玩转地铁",
                packageName = "com.hskj.palmmetro",
                viewId = "com.hskj.palmmetro:id/tt_splash_skip_btn"
            ),
            JumpApp(
                name = "咪咕音乐",
                packageName = "cmccwm.mobilemusic",
                viewId = "cmccwm.mobilemusic:id/bt_skip_ad"
            ),
            JumpApp(
                name = "今日头条",
                packageName = "com.ss.android.article.news",
                viewId = "com.ss.android.article.news:id/dpo"
            ),
            JumpApp(
                name = "动卡空间",
                packageName = "com.citiccard.mobilebank",
                viewId = "com.citiccard.mobilebank:id/btn_skip"
            ),
            JumpApp(
                name = "买单吧",
                packageName = "com.bankcomm.maidanba",
                viewId = "com.bankcomm.maidanba:id/ll_ad_skip"
            ),
            JumpApp(
                name = "掌上生活",
                packageName = "com.cmbchina.ccd.pluto.cmbActivity",
                viewId = "com.cmbchina.ccd.pluto.cmbActivity:id/img_cancel"
            ),
            JumpApp(
                name = "京东金融",
                packageName = "com.jd.jrapp",
                viewId = "com.jd.jrapp:id/btn_jump"
            ),
            JumpApp(
                name = "百度手机助手",
                packageName = "com.baidu.appsearch",
                viewId = "com.baidu.appsearch:id/launcher_skip_layout"
            ),
            JumpApp(
                name = "美团外卖",
                packageName = "com.sankuai.meituan.takeoutnew",
                viewId = "com.sankuai.meituan.takeoutnew:id/ll_skip"
            )
        )
    }
}
