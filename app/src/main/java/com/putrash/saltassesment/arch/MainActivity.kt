package com.putrash.saltassesment.arch

import android.os.Bundle
import com.putrash.saltassesment.R
import com.putrash.saltassesment.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun setView(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        return
    }
}