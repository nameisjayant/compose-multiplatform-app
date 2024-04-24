package com.nameisjayant.kmpapp.jsonplaceholder.feature.ui.viewmodel.states

data class PostStates<T>(
    val data: List<T> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)
