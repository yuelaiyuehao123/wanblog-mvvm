package com.wanblog.ui.fragment

import android.os.Bundle
import com.wanblog.R
import com.wanblog.base.BaseFragment
import com.wanblog.databinding.FragmentMainBinding
import com.wanblog.ext.init
import com.wanblog.ext.initMain
import com.wanblog.ext.interceptLongClick
import com.wanblog.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    override fun layoutId(): Int = R.layout.fragment_main

    override fun initView(savedInstanceState: Bundle?) {
        //初始化viewpager2
        mainViewpager.initMain(this)
        //初始化 bottomBar
        mainBottom.init {
            when (it) {
                R.id.menu_home -> mainViewpager.setCurrentItem(0, false)
                R.id.menu_me -> mainViewpager.setCurrentItem(1, false)
            }
        }
        mainBottom.interceptLongClick(
            R.id.menu_home,
            R.id.menu_me
        )
    }

//    override fun createObserver() {
//        appViewModel.appColor.observeInFragment(this, Observer {
//            setUiTheme(it, mainBottom)
//        })
//    }

}