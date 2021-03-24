package com.example.a3dtourarestate.presenter

import android.util.Log
import com.example.a3dtourarestate.model.StateObject
import com.example.a3dtourarestate.model.mvi.EstateActionState
import com.example.a3dtourarestate.model.mvi.EstateViewState
import com.example.a3dtourarestate.view.MviEstateView
import com.google.gson.Gson
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MviEstatePresenter : MviBasePresenter<MviEstateView, EstateViewState>() {

    override fun bindIntents() {

        // Default loading of page
        val estatesState: Observable<EstateActionState> = intent(MviEstateView::emitFirstTimeLoadEvent)
            .subscribeOn(Schedulers.io())
            .debounce(200, TimeUnit.MILLISECONDS)
            .doOnNext { Log.d("Action", "First time page load event.") }
            .flatMap { GetEstatesUseCase.getEstates() }
            .observeOn(AndroidSchedulers.mainThread())

        // Load more estates
        val loadMoreEstatesState: Observable<EstateActionState> = intent(MviEstateView::emitLoadMoreEstatesEvent)
            .subscribeOn(Schedulers.io())
            .debounce(200, TimeUnit.MILLISECONDS)
            .doOnNext { Log.d("Action", "Load more estates event.") }
            .switchMap { GetEstatesUseCase.getMoreEstates() }
            .observeOn(AndroidSchedulers.mainThread())

        // Loading page using pull to refresh
        val pullToRefreshState: Observable<EstateActionState> = intent(MviEstateView::emitPullToRefreshEvent)
            .subscribeOn(Schedulers.io())
            .debounce(200, TimeUnit.MILLISECONDS)
            .doOnNext { Log.d("Action", "Pull to refresh event.") }
            .switchMap { GetEstatesUseCase.getEstatesByPTR() }
            .observeOn(AndroidSchedulers.mainThread())

        val allViewState: Observable<EstateActionState> = Observable.merge(
            estatesState,
            loadMoreEstatesState,
            pullToRefreshState)

        val initializeState = EstateViewState(isPageLoading = true)
        val stateObservable = allViewState
            .scan(initializeState, this::viewStateReducer)
            .doOnNext { Log.d("State", Gson().toJson(it)) }

        subscribeViewState(
            stateObservable,
            MviEstateView::displayEstates
        )
    }

    private fun viewStateReducer(previousState: EstateViewState,
                                 currentState: EstateActionState): EstateViewState {
        return when (currentState) {
            EstateActionState.LoadingState -> {
                previousState
                    .copy()
                    .isPageLoading(true)
                    .isPullToRefresh(false)
                    .isMoreEstatesLoading(false)
                    .data(null)
                    .error(null)
                    .build()
            }
            EstateActionState.PullToRefreshState -> {
                previousState
                    .copy()
                    .isPullToRefresh(true)
                    .isPageLoading(false)
                    .isMoreEstatesLoading(false)
                    .data(null)
                    .error(null)
                    .build()
            }
            EstateActionState.LoadMoreState -> {
                val bridgeObject =
                    StateObject(type = StateObject.TYPE_MORE_ESTATE, isLoading = true)
                val newEstatesArray = ArrayList<StateObject>()
                newEstatesArray.addAll(previousState.estatesObject!!.estates)
                newEstatesArray.removeAt(newEstatesArray.lastIndex)
                newEstatesArray.add(bridgeObject)
                previousState.estatesObject.estates = newEstatesArray
                previousState
                    .copy()
                    .isMoreEstatesLoading(true)
                    .isPullToRefresh(false)
                    .isPageLoading(false)
                    .data(previousState.estatesObject)
                    .build()
            }
            is EstateActionState.DataState -> {
                // Adds the item to load the remaining estatesObject while loading
                val bridgeObject = StateObject(type = StateObject.TYPE_MORE_ESTATE, itemCount = 3)
                val newEstatesArray = ArrayList<StateObject>()
                newEstatesArray.addAll(currentState.estatesObject.estates)
                newEstatesArray.add(bridgeObject)
                currentState.estatesObject.estates = newEstatesArray
                previousState
                    .copy()
                    .data(currentState.estatesObject)
                    .build()
            }
            is EstateActionState.ErrorState -> {
                previousState
                    .copy()
                    .data(null)
                    .error(currentState.error)
                    .build()
            }
            is EstateActionState.LoadMoreDataState -> {
                val bridgeObject = StateObject(type = StateObject.TYPE_MORE_ESTATE, itemCount = 3)
                val newEstatesArray = ArrayList<StateObject>()
                newEstatesArray.addAll(previousState.estatesObject!!.estates)
                newEstatesArray.removeAt(newEstatesArray.lastIndex)
                newEstatesArray.addAll(currentState.estateObjects.estates)
                newEstatesArray.add(bridgeObject)
                currentState.estateObjects.estates = newEstatesArray
                previousState
                    .copy()
                    .isPageLoading(false)
                    .isPullToRefresh(false)
                    .data(currentState.estateObjects)
                    .build()
            }
            is EstateActionState.LoadMoreErrorState -> {
                val bridgeObject =
                    StateObject(type = StateObject.TYPE_MORE_ESTATE, error = currentState.error)
                val newEstatesArray = ArrayList<StateObject>()
                newEstatesArray.addAll(previousState.estatesObject!!.estates)
                newEstatesArray.removeAt(newEstatesArray.lastIndex)
                newEstatesArray.add(bridgeObject)
                previousState.estatesObject.estates = newEstatesArray
                previousState
                    .copy()
                    .isPageLoading(false)
                    .isPullToRefresh(false)
                    .data(previousState.estatesObject)
                    .build()
            }
        }
    }

    public override fun getViewStateObservable(): Observable<EstateViewState> {
        return super.getViewStateObservable()
    }

}