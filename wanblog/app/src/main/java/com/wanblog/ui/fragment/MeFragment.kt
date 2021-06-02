package com.wanblog.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.wanblog.R
import com.wanblog.WanBlogApp
import com.wanblog.base.BaseFragment
import com.wanblog.ext.jumpByLogin
import com.wanblog.ext.nav
import com.wanblog.ext.navigateAction
import com.wanblog.util.UserUtil
import com.wanblog.viewmodel.MeViewModel
import kotlinx.android.synthetic.main.dialog_logout.view.*
import kotlinx.android.synthetic.main.fragment_me.*

class MeFragment : BaseFragment<MeViewModel>() {

    override fun layoutId(): Int = R.layout.fragment_me

    override fun initView(savedInstanceState: Bundle?) {

        me_linear.setOnClickListener {
            nav().jumpByLogin {
                showLogoutDialog()
            }
        }
        ll_me_collect.setOnClickListener {
            nav().jumpByLogin {
                it.navigateAction(R.id.action_mainfragment_to_collectActivity)
            }
        }
        ll_me_wenzhang.setOnClickListener {
            nav().jumpByLogin {
                Toast.makeText(activity, "点击了文章.", Toast.LENGTH_LONG).show()
            }
        }
        ll_me_shezhi.setOnClickListener {
            nav().navigateAction(R.id.action_mainfragment_to_settingActivity)
        }

    }

    override fun createObserver() {

        appViewModel.mUserInfo.observe(this@MeFragment, Observer {
            if (it == null) {
                UserUtil.saveUser(WanBlogApp.instance, null)
                tv_me_name.text = "请登录"
            } else {
                tv_me_name.text = it.username
            }
        })

        mViewModel.logoutResult.observe(this@MeFragment, Observer {
            appViewModel.mUserInfo.value = null
        })

    }

    override fun lazyLoadData() {

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