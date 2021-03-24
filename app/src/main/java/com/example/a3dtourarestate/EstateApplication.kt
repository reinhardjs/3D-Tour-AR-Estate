package com.example.a3dtourarestate

import android.app.Application
import android.content.Context
import com.example.a3dtourarestate.di.DependencyInjector
import com.example.a3dtourarestate.di.DependencyInjectorImpl

class EstateApplication : Application() {

    private val dependencyInjection: DependencyInjector = DependencyInjectorImpl()

    companion object {
        fun getDependencyInjection(context: Context): DependencyInjector {
            return (context.applicationContext as EstateApplication).dependencyInjection
        }
    }

}