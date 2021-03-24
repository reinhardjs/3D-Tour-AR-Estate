package com.example.a3dtourarestate.api

import com.example.a3dtourarestate.model.Estate
import com.squareup.moshi.Moshi

class EstateRepositoryImpl : EstateRepository {
    val jsonEstate:String = "{\"id\":1,\"title\":\"Rumah\",\"description\":\"Tipe 50\",\"price\":\"100000000\"}"

    override fun loadEstate(): Estate {
        val moshi = Moshi.Builder().build()

        val json = jsonEstate
        val jsonAdapter = moshi.adapter<Estate>(Estate::class.java)
        return jsonAdapter.fromJson(json)
    }

    override fun loadEstateList(): List<Estate> {
        return ArrayList()
    }
}