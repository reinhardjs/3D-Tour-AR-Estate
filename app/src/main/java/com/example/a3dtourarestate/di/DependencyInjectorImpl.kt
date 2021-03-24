package com.example.a3dtourarestate.di

import com.example.a3dtourarestate.api.EstateRepository
import com.example.a3dtourarestate.api.EstateRepositoryImpl
import com.example.a3dtourarestate.presenter.MviEstatePresenter

class DependencyInjectorImpl : DependencyInjector {

    private lateinit var mviEstatePresenter: MviEstatePresenter

    override fun estateRepository(): EstateRepository {
        return EstateRepositoryImpl()
    }

    override fun newEstatePresenter(): MviEstatePresenter {
        mviEstatePresenter = MviEstatePresenter()
        return mviEstatePresenter
    }

}