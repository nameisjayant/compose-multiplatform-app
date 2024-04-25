package com.nameisjayant.kmpapp.api.di

import com.nameisjayant.kmpapp.api.data.network.PostApiService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }
    single { PostApiService(get()) }
}


fun initKoin() {
    startKoin {
        modules(
            dataModule,
        )
    }
}
