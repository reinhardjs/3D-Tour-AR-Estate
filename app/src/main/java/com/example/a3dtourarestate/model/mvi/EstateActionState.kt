package com.example.a3dtourarestate.model.mvi

import com.example.a3dtourarestate.model.EstatesObject

sealed class EstateActionState {

    object LoadingState: EstateActionState()
    object LoadMoreState: EstateActionState()
    object PullToRefreshState: EstateActionState()

    data class DataState(val estatesObject: EstatesObject): EstateActionState()
    data class LoadMoreDataState(val estateObjects: EstatesObject): EstateActionState()
    data class ErrorState(val error: Throwable): EstateActionState()
    data class LoadMoreErrorState(val error: Throwable): EstateActionState()

}