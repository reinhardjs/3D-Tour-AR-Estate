package com.example.a3dtourarestate.presenter

import com.example.a3dtourarestate.base.BasePresenter
import com.example.a3dtourarestate.base.BaseView
import com.example.a3dtourarestate.model.Estate

interface DetailContract {

    interface Presenter : BasePresenter {
        fun loadEstate()
        fun start3DTour(id: String)
    }

    interface View : BaseView<Presenter> {
        fun initView(estate: Estate)
        fun showLoading()
        fun startUnityActivity(id: String)
    }

}