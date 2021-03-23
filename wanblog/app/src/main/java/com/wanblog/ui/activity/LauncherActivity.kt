package com.wanblog.ui.activity

import android.content.Intent
import android.os.Bundle
import com.wanblog.R
import com.wanblog.WanBlogApp
import com.wanblog.base.BaseActivity
import com.wanblog.base.BaseViewModel
import com.wanblog.databinding.ActivityLauncherBinding
import com.wanblog.ext.view.gone
import com.wanblog.ext.view.visible
import com.wanblog.ui.adapter.LauncherBannerAdapter
import com.wanblog.util.UserUtil
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_launcher.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class LauncherActivity : BaseActivity<BaseViewModel, ActivityLauncherBinding>() {

    override fun layoutId(): Int = R.layout.activity_launcher

    private var mList = mutableListOf("Wan", "Blog")

    override fun initView(savedInstanceState: Bundle?) {
        //防止出现按Home键回到桌面时，再次点击重新进入该界面bug
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT !== 0) {
            finish()
            return
        }
        mDatabind.click = ProxyClick()

        if (UserUtil.isFirst(WanBlogApp.instance)) {
            //是第一次打开App 显示引导页
            welcome_image.gone()
            val adapter = LauncherBannerAdapter(mList, this)
            banner_launcher.let {
                it.indicator = CircleIndicator(this)
                it.setBannerRound(0f)
                it.isAutoLoop(false)
                it.addOnPageChangeListener(object : OnPageChangeListener {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }

                    override fun onPageSelected(position: Int) {
                        if (position == mList.size - 1) {
                            welcomeJoin.visible()
                        }
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                    }
                })
                it.adapter = adapter
            }
        } else {
            //不是第一次打开App 1.5秒后自动跳转到主页
            welcome_image.visible()
            doAsync {
                Thread.sleep(1500)
                uiThread {
                    startActivity<MainActivity>()
                    finish()
                    //带点渐变动画
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
            }
        }
    }

    inner class ProxyClick {
        fun toMain() {
            UserUtil.saveIsFirst(WanBlogApp.instance, false)
            startActivity<MainActivity>()
            finish()
            //带点渐变动画
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

}