package com.nameisjayant.kmpapp.mesume.di

import com.nameisjayant.kmpapp.mesume.data.InMemoryMuseumStorage
import com.nameisjayant.kmpapp.mesume.data.KtorMuseumApi
import com.nameisjayant.kmpapp.mesume.data.MuseumApi
import com.nameisjayant.kmpapp.mesume.data.MuseumRepository
import com.nameisjayant.kmpapp.mesume.data.MuseumStorage
import com.nameisjayant.kmpapp.mesume.screens.detail.DetailScreenModel
import com.nameisjayant.kmpapp.mesume.screens.list.ListScreenModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<MuseumApi> { KtorMuseumApi(get()) }
    single<MuseumStorage> { InMemoryMuseumStorage() }
    single {
        MuseumRepository(get(), get()).apply {
            initialize()
        }
    }
}

val screenModelsModule = module {
    factoryOf(::ListScreenModel)
    factoryOf(::DetailScreenModel)
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            screenModelsModule,
        )
    }
}
