package com.example.a3dtourarestate.presenter

import com.example.a3dtourarestate.api.EstateRepository
import com.example.a3dtourarestate.di.DependencyInjector

class DetailPresenter(view: DetailContract.View, dependencyInjector: DependencyInjector) :
    DetailContract.Presenter {

    private val estateRepository: EstateRepository = dependencyInjector.estateRepository()
    private val view: DetailContract.View? = view

    override fun loadEstate() {
        val estate = estateRepository.loadEstate()
        view?.initView(estate)
    }

    override fun start3DTour(id: String) {
        view?.showLoading()
        view?.startUnityActivity(id)
    }

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

}