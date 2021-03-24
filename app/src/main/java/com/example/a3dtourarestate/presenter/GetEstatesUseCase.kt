 package com.example.a3dtourarestate.presenter

import   com.example.a3dtourarestate.api.EstateApi
import com.example.a3dtourarestate.api.EstateApiEndpoint
import com.example.a3dtourarestate.model.mvi.EstateActionState
import io.reactivex.Observable

object GetEstatesUseCase {

    /*Note: All are same api service calls,
      only the Loading, data, error states differs. */

    // Default Loading
    fun getEstates(): Observable<EstateActionState> {
        val endpoint = EstateApi.getClient().create(EstateApiEndpoint::class.java)
        return endpoint.getEstates("", 3)
            .map<EstateActionState> { EstateActionState.DataState(it) }
            .startWith(EstateActionState.LoadingState)
            .onErrorReturn { (EstateActionState.ErrorState(it)) }
    }

    // Load more restaurants
    fun getMoreEstates(): Observable<EstateActionState> {
        val endpoint = EstateApi.getClient().create(EstateApiEndpoint::class.java)
        return endpoint.getEstates("", 3)
            .map<EstateActionState> { EstateActionState.LoadMoreDataState(it) }
            .startWith(EstateActionState.LoadMoreState)
            .onErrorReturn { (EstateActionState.LoadMoreErrorState(it)) }
    }

    // PullToRefresh
    fun getEstatesByPTR() : Observable<EstateActionState> {
        val endpoint = EstateApi.getClient().create(EstateApiEndpoint::class.java)
        return endpoint.getEstates("", 3)
            .map<EstateActionState> { EstateActionState.DataState(it) }
            .startWith(EstateActionState.PullToRefreshState)
            .onErrorReturn{(EstateActionState.ErrorState(it))}
    }
}