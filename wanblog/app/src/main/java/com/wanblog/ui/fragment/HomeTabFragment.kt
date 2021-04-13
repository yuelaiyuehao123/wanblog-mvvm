package com.wanblog.ui.fragment

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jakewharton.rxbinding4.view.clicks
import com.kingja.loadsir.core.LoadService
import com.wanblog.R
import com.wanblog.base.BaseFragment
import com.wanblog.data.model.bean.BlogBean
import com.wanblog.ext.loadServiceInit
import com.wanblog.ext.showEmpty
import com.wanblog.model.bean.Top3Bean
import com.wanblog.ui.adapter.BaseDelegateAdapter
import com.wanblog.ui.adapter.HomeBannerAdapter
import com.wanblog.viewmodel.HomeTabViewModel
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_tab.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.concurrent.TimeUnit

class HomeTabFragment : BaseFragment<HomeTabViewModel>() {

    //界面状态管理者
    private lateinit var mLoadsir: LoadService<Any>

    //vLayout
    val VLAYOUT_BANNER = 1         //轮播图
    val VLAYOUT_GRID = 2           //网格
    val VLAYOUT_LIST = 3           //普通列表

    //总适配器
    var mDelegateAdapter: DelegateAdapter? = null

    // 存放各个模块的适配器
    private var mAdapters: MutableList<DelegateAdapter.Adapter<*>> = mutableListOf()

    // 前三名数据
    private var mTop3List: MutableList<Top3Bean> = mutableListOf()

    // 普通列表数据
    private var mBlogList: MutableList<BlogBean> = mutableListOf()

    override fun layoutId(): Int = R.layout.fragment_home_tab

    companion object {
        fun newInstance(cid: Int, isNew: Boolean): HomeTabFragment {
            val args = Bundle()
            args.putInt("cid", cid)
            args.putBoolean("isNew", isNew)
            val fragment = HomeTabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        mLoadsir = loadServiceInit(refreshLayout_home_page) {
            // 点击重试时触发的操作
            refreshLayout_home_page.autoRefresh()
        }
        initRecyclerView()
    }

    override fun lazyLoadData() {
        refreshLayout_home_page.autoRefresh()
    }

    override fun createObserver() {

        mViewModel.top3ListLiveData.observe(viewLifecycleOwner, Observer {
            mTop3List = it;
            mViewModel.getBlogList(true)
        })

        mViewModel.blogListLiveData.observe(viewLifecycleOwner, Observer {
            val isRefresh = mViewModel.isRefreshLiveData.value!!
            if (isRefresh) {
                doAsync {
                    Thread.sleep(1000)
                    uiThread {
                        fab_home.show(true)
                    }
                }
            }
            if (it.isEmpty()) {
                if (isRefresh) {
                    mLoadsir.showEmpty()
                } else {
                    Toast.makeText(activity, "没有更多数据了", Toast.LENGTH_LONG).show()
                }
            } else {
                if (isRefresh) {
                    mBlogList.clear()
                    mLoadsir.showSuccess()
                }
                mBlogList.addAll(it)
                mAdapters.clear()
                val bannerAdapter = initBannerAdapter(mTop3List)
                mAdapters.add(bannerAdapter)
                val listAdapter = initListAdapter(mBlogList)
                mAdapters.add(listAdapter)
                mDelegateAdapter!!.setAdapters(mAdapters)
                mDelegateAdapter!!.notifyDataSetChanged()
            }
            refreshLayout_home_page.finishRefresh()
            refreshLayout_home_page.finishLoadMore()
        })
    }

    private fun initRecyclerView() {
        //初始化
        //创建VirtualLayoutManager对象
        val layoutManager = VirtualLayoutManager(mActivity)
        rv_home_page.layoutManager = layoutManager

        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        val viewPool = RecyclerView.RecycledViewPool()
        viewPool.setMaxRecycledViews(0, 20)
        rv_home_page.setRecycledViewPool(viewPool)

        //设置适配器
        mDelegateAdapter = DelegateAdapter(layoutManager, true)
        rv_home_page.adapter = mDelegateAdapter

        refreshLayout_home_page.setEnableLoadMore(true)
        refreshLayout_home_page.setEnableRefresh(true)
        refreshLayout_home_page.setOnRefreshListener {
            mViewModel.getTop3List()
        }

        refreshLayout_home_page.setOnLoadMoreListener {
            mViewModel.getBlogList(false)
        }
    }

    /**
     * banner
     */
    private fun initBannerAdapter(top3BeanItems: MutableList<Top3Bean>): BaseDelegateAdapter {
        return object : BaseDelegateAdapter(
            activity,
            LinearLayoutHelper(),
            R.layout.item_home_banner_layout,
            1,
            VLAYOUT_BANNER
        ) {
            override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                val adapter = HomeBannerAdapter(top3BeanItems, context!!)
                val banner = holder.getView<Banner<Any, HomeBannerAdapter>>(R.id.banner)
                banner.let {
                    it.indicator = CircleIndicator(context!!)
                    it.setBannerRound(0f)
                    it.setLoopTime(1000 * 30)
                    it.adapter = adapter
                    it.setOnBannerListener(object : OnBannerListener<Top3Bean> {
                        override fun OnBannerClick(top3Bean: Top3Bean, position: Int) {
                            Toast.makeText(context!!, "点击了" + top3Bean.username, Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
                }
            }
        }
    }

    /**
     * 普通列表item
     */
    private fun initListAdapter(blogList: MutableList<BlogBean>): BaseDelegateAdapter {
        return object : BaseDelegateAdapter(
            activity,
            LinearLayoutHelper(),
            R.layout.item_home_list,
            blogList.size,
            VLAYOUT_LIST
        ) {
            override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                val blog = blogList[position]
                val ll_home_list_item = holder.getView<LinearLayout>(R.id.ll_home_list_item)
                val tv_home_list_item_title =
                    holder.getView<AppCompatTextView>(R.id.tv_home_list_item_title)
                val tv_home_list_item_description =
                    holder.getView<AppCompatTextView>(R.id.tv_home_list_item_description)
                val iv_home_list_item_avatar =
                    holder.getView<AppCompatImageView>(R.id.iv_home_list_item_avatar)
                val tv_home_list_item_username =
                    holder.getView<AppCompatTextView>(R.id.tv_home_list_item_username)

                Glide.with(this@HomeTabFragment)
                    .load(blog.avatar)
                    .placeholder(R.drawable.ic_user_avatar_placeholder)
                    .apply(RequestOptions.bitmapTransform(CircleCrop()))
                    .transition(DrawableTransitionOptions.withCrossFade(500))
                    .into(iv_home_list_item_avatar)

                tv_home_list_item_title.text = blog.title
                tv_home_list_item_description.text = blog.description
                tv_home_list_item_username.text = blog.username

                //1秒钟之内禁用重复点击
                ll_home_list_item.clicks()
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe {
//                        startActivity<BlogActivity>(
//                            BlogActivity.blog_is_new_key to false,
//                            BlogActivity.blog_id_key to blog.bid,
//                            BlogActivity.blog_user_id_key to blog.uid
//                        )
                    }
            }
        }
    }

}