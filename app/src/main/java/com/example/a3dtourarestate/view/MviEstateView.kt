package com.example.a3dtourarestate.view

import com.example.a3dtourarestate.model.mvi.EstateViewState
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface MviEstateView : MvpView {

    fun emitFirstTimeLoadEvent(): Observable<Boolean>
    fun emitPullToRefreshEvent(): Observable<Unit>
    fun emitLoadMoreEstatesEvent(): Observable<String>
    fun displayEstates(restaurantViewState: EstateViewState)

}