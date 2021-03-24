package com.example.a3dtourarestate.model.mvi

import com.example.a3dtourarestate.model.EstatesObject

data class EstateViewState(
    val isPageLoading: Boolean = false,
    val isPullToRefresh: Boolean = false,
    val isMoreEstatesLoading: Boolean = false,
    val estatesObject: EstatesObject? = null,
    val error: Throwable? = null
) {

    fun copy(): Builder {
        return Builder(this)
    }

    class Builder(estateViewState: EstateViewState) {

        private var isPageLoading = estateViewState.isPageLoading
        private var isPullToRefresh = estateViewState.isPullToRefresh
        private var isMoreEstatesLoading = estateViewState.isMoreEstatesLoading
        private var estatesObject: EstatesObject? = estateViewState.estatesObject
        private var error: Throwable? = estateViewState.error

        fun isPageLoading(isPageLoading: Boolean): Builder {
            this.isPageLoading = isPageLoading
            return this
        }

        fun isPullToRefresh(isPullToRefresh: Boolean): Builder {
            this.isPullToRefresh = isPullToRefresh
            return this
        }

        fun isMoreEstatesLoading(isMoreRestaurantsLoading: Boolean): Builder {
            this.isMoreEstatesLoading = isMoreRestaurantsLoading
            return this
        }

        fun data(newRestaurantsObject: EstatesObject?): Builder {
            estatesObject = newRestaurantsObject
            return this
        }

        fun error(error: Throwable?): Builder {
            this.error = error
            return this
        }

        fun build(): EstateViewState {
            return EstateViewState(
                isPageLoading,
                isPullToRefresh,
                isMoreEstatesLoading,
                estatesObject,
                error
            )
        }

    }

}