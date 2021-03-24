package com.example.a3dtourarestate.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dtourarestate.model.StateObject

abstract class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun setData(stateObject: StateObject)
}