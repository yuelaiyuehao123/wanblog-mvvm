package com.wanblog.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.wanblog.R
import com.wanblog.WanBlogApp
import com.wanblog.base.BaseFragment
import com.wanblog.databinding.FragmentMeBinding
import com.wanblog.ext.jumpByLogin
import com.wanblog.ext.nav
import com.wanblog.ext.navigateAction
import com.wanblog.ext.util.notNull
import com.wanblog.util.ColorUtil
import com.wanblog.util.LogUtils
import com.wanblog.util.UserUtil
import com.wanblog.viewmodel.MeViewModel
import kotlinx.android.synthetic.main.dialog_logout.view.*

class MeFragment : BaseFragment<MeViewModel, FragmentMeBinding>() {

    override fun layoutId(): Int = R.layout.fragment_me

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.vm = mViewModel
        mDatabind.click = ProxyClick()
    }

    override fun createObserver() {
        appViewModel.run {
            userName.observeInFragment(this@MeFragment, Observer {
                mViewModel.userName.value = it
                mViewModel.imageUrl.value = UserUtil.getUserImage(mActivity)
            })
        }
    }

    override fun initData() {
        val name: String
        val imageUrl: String
        if (UserUtil.isLogin(mActivity)) {
            name = UserUtil.getUserName(mActivity)
            imageUrl = UserUtil.getUserImage(mActivity)
        } else {
            name = "请登录"
            imageUrl = ""
        }
        mViewModel.userName.value = name
        mViewModel.imageUrl.value = imageUrl
    }

    inner class ProxyClick {

        fun login() {
            nav().jumpByLogin {
                showLogoutDialog()
            }
        }

        fun collect() {
            nav().jumpByLogin {
                it.navigateAction(R.id.action_mainfragment_to_collectActivity)
            }
        }

        fun ariticle() {
        }

        fun setting() {
            nav().navigateAction(R.id.action_mainfragment_to_settingActivity)
        }

    }

    private fun showLogoutDialog() {
        val dialog = Dialog(mActivity, R.style.MyDialog)
        val view = View.inflate(mActivity, R.layout.dialog_logout, null)
        dialog.setContentView(view)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
        view.dialog_logout_cancel.setOnClickListener {
            dialog.dismiss()
        }
        view.dialog_logout_confirm.setOnClickListener {
            mViewModel.logout()
            dialog.dismiss()
        }
    }

}