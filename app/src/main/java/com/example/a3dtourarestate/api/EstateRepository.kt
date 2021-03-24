package com.example.a3dtourarestate.api

import com.example.a3dtourarestate.model.Estate

interface EstateRepository {

    fun loadEstate(): Estate
    fun loadEstateList(): List<Estate>

}