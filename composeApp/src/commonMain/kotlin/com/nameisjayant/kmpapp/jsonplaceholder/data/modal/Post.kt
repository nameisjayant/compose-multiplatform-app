package com.nameisjayant.kmpapp.jsonplaceholder.data.modal

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int? = null,
    val title: String? = null,
    val body:String? = null
)
