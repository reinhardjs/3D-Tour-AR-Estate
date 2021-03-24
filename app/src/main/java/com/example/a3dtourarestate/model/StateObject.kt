package com.example.a3dtourarestate.model

data class StateObject (
    val type: String,
    val estate: Estate? = null,
    val itemCount: Int? = null,
    val isLoading: Boolean? = null,
    val error: Throwable? = null
) {
    companion object {
        const val TYPE_MORE_ESTATE = "MORE_ESTATE"
    }
}