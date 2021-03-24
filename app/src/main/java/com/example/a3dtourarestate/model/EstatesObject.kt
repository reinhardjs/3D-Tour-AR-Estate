package com.example.a3dtourarestate.model

import com.google.gson.annotations.SerializedName

data class EstatesObject(@SerializedName("estates") var estates: List<StateObject>)