package com.example.a3dtourarestate.model

import com.google.gson.annotations.SerializedName

data class Estate(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String)