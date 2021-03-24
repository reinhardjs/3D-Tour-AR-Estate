package com.example.a3dtourarestate.api

import com.example.a3dtourarestate.model.EstatesObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface EstateApiEndpoint {

    @GET("api/v2.1/search")
    fun getEstates(@Query("query") query: String, @Query("count") count: Int): Observable<EstatesObject>
}