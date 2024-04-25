package com.nameisjayant.kmpapp.api.data.network

import com.nameisjayant.kmpapp.api.data.modal.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PostApiService(
    private val client: HttpClient
) {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/posts"
    }

    suspend fun getPosts(): List<Post> {
        return try {
            client.get(BASE_URL).body()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}