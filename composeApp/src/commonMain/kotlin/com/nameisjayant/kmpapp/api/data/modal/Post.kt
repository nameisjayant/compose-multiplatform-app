package com.nameisjayant.kmpapp.api.data.modal

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int? = null,
    val title: String? = null,
    val body:String? = null
)
