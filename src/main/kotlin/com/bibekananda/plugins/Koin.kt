package com.bibekananda.plugins

import com.bibekananda.di.koinModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin(){
    install(Koin){
        modules(koinModule)
    }
}