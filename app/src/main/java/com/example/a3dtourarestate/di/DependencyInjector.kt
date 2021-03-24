package com.example.a3dtourarestate.di

import com.example.a3dtourarestate.api.EstateRepository
import com.example.a3dtourarestate.presenter.MviEstatePresenter

interface DependencyInjector {

    fun estateRepository(): EstateRepository

    fun newEstatePresenter(): MviEstatePresenter

}