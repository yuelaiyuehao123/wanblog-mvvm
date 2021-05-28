package com.wanblog.ui.activity

import android.os.Bundle
import android.view.View
import com.wanblog.R
import com.wanblog.WanBlogApp
import com.wanblog.base.BaseActivity
import com.wanblog.base.BaseViewModel
import com.wanblog.ui.adapter.LauncherBannerAdapter
import com.wanblog.util.UserUtil
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_launcher.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class LauncherActivity : BaseActivity<BaseViewModel>() {

    override fun layoutId(): Int = R.layout.activity_launcher

    private var mList = mutableListOf("Wan", "Blog")

    override fun createObserver() {
    }

    override fun initView(savedInstanceState: Bundle?) {

        bt_launcher_goto_main.setOnClickListener {
            gotoMainActivity()
        }

        if (UserUtil.isFirst(WanBlogApp.instance)) {
            //是第一次打开App 显示引导页
            iv_launcher.visibility = View.GONE
            val adapter = LauncherBannerAdapter(mList, this)
            banner_launcher.indicator = CircleIndicator(this)
            banner_launcher.setBannerRound(0f)
            banner_launcher.isAutoLoop(false)
            banner_launcher.addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    if (position == mList.size - 1) {
                        bt_launcher_goto_main.visibility = View.VISIBLE
                    }
                }

                override fun onPageScrollStateChanged(state: Int) {
                }
            })
            banner_launcher.adapter = adapter
        } else {
            //不是第一次打开App 1.5秒后自动跳转到主页
            iv_launcher.visibility = View.VISIBLE
            doAsync {
                Thread.sleep(1500)
                uiThread {
                    gotoMainActivity();
                }
            }
        }
    }

    private fun gotoMainActivity() {
        UserUtil.saveIsFirst(WanBlogApp.instance, false)
        startActivity<MainActivity>()
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}