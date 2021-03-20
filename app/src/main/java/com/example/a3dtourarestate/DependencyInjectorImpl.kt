package com.example.a3dtourarestate

class DependencyInjectorImpl : DependencyInjector {
    override fun estateRepository(): EstateRepository {
        return EstateRepositoryImpl()
    }
}