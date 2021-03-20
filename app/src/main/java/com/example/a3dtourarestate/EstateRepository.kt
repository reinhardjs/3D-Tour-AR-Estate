package com.example.a3dtourarestate

import com.example.a3dtourarestate.model.Estate

interface EstateRepository {

    fun loadEstate(): Estate
    fun loadEstateList(): List<Estate>

}