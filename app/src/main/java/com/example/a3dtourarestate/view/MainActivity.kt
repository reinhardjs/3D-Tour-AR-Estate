package com.example.a3dtourarestate.view

import android.os.Bundle
import com.example.a3dtourarestate.EstateApplication
import com.example.a3dtourarestate.R
import com.example.a3dtourarestate.model.mvi.EstateViewState
import com.example.a3dtourarestate.presenter.MviEstatePresenter
import com.hannesdorfmann.mosby3.mvi.MviActivity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MainActivity : MviActivity<MviEstateView, MviEstatePresenter>(), MviEstateView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val intent = Intent(this, UnityPlayerActivity::class.java)
//        intent.putExtra("modelUrl", "https://gitlab.com/reinhardjonathansilalahi/3dmodel/-/raw/master/3dmodel.glb");
//
//        startActivity(intent)
    }

    override fun createPresenter(): MviEstatePresenter {
        return EstateApplication.getDependencyInjection(this).newEstatePresenter()
    }

    override fun emitFirstTimeLoadEvent(): Observable<Boolean> = Observable.just(true).subscribeOn(
        Schedulers.io()
    )

    override fun emitPullToRefreshEvent(): Observable<Unit> {
        TODO("Not yet implemented")
    }

    override fun emitLoadMoreEstatesEvent(): Observable<String> {
        TODO("Not yet implemented")
    }

    override fun displayEstates(restaurantViewState: EstateViewState) {
        TODO("Not yet implemented")
    }
}