package com.wanblog.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kingja.loadsir.core.LoadService
import com.wanblog.R
import com.wanblog.base.BaseFragment
import com.wanblog.ext.*
import com.wanblog.network.manager.NetState
import com.wanblog.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<HomeViewModel>() {

    //fragment集合
    private var mFragments: ArrayList<Fragment> = arrayListOf()

    //标题集合
    private var mDataList: ArrayList<String> = arrayListOf()

    //界面状态管理者
    private lateinit var mLoadsir: LoadService<Any>

    override fun layoutId(): Int = R.layout.fragment_home

    override fun initView(savedInstanceState: Bundle?) {
        //状态页配置
        mLoadsir = loadServiceInit(view_pager) {
            //点击重试时触发的操作
            mLoadsir.showLoading()
        }
        //初始化viewpager2
        view_pager.init(this, mFragments)
        //初始化 magic_indicator
        magic_indicator.bindViewPager2(view_pager, mDataList)
    }

    /**
     * 懒加载
     */
    override fun lazyLoadData() {
        //设置界面 加载中
        mLoadsir.showLoading()
        //请求标题数据
        mViewModel.getTabData()
    }

    override fun createObserver() {
        mViewModel.titleData.observe(viewLifecycleOwner, Observer {
            mDataList.clear()
            mFragments.clear()
            mDataList.addAll(it)
            repeat(it.size) {
                mFragments.add(HomeTabFragment.newInstance(0, false))
            }
            magic_indicator.navigator.notifyDataSetChanged()
            view_pager.adapter?.notifyDataSetChanged()
            view_pager.offscreenPageLimit = mFragments.size
            mLoadsir.showSuccess()
        })
    }

}