package com.wanblog.ui.view

import com.kingja.loadsir.callback.Callback
import com.wanblog.R

class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}