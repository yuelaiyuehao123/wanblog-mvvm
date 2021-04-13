package com.wanblog.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.wanblog.R
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
        appViewModel.run {
            userName.observeInFragment(this@MeFragment, Observer {
                mViewModel.userName.value = it
                mViewModel.imageUrl.value = UserUtil.getUserImage(mActivity)
            })
        }
    }

    override fun lazyLoadData() {
    }

    override fun initData() {
        val name: String
        val imageUrl: String
        if (UserUtil.isLogin(mActivity)) {
            name = UserUtil.getUserName(mActivity)
//            imageUrl = UserUtil.getUserImage(mActivity)
            imageUrl = "http://test-public-cn.bj.bcebos.com/sota/test/500939980.jpg"
        } else {
            name = "请登录"
            imageUrl = ""
        }
        mViewModel.userName.value = name
        mViewModel.imageUrl.value = imageUrl
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