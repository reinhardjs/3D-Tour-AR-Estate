package com.example.a3dtourarestate

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