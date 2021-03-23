package com.wanblog.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wanblog.R
import com.youth.banner.adapter.BannerAdapter
import kotlinx.android.synthetic.main.item_launcher_banner_item.view.*

class LauncherBannerAdapter(title: MutableList<String>, val context: Context) :
    BannerAdapter<String, LauncherBannerAdapter.ViewHolder>(title) {



    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_launcher_banner_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindView(holder: ViewHolder, title: String, position: Int, size: Int) {
        holder.bindData(title)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(title: String) {
            itemView.tv_launcher_banner.text = title
        }
    }

}