package com.nameisjayant.kmpapp.jsonplaceholder.feature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nameisjayant.kmpapp.jsonplaceholder.data.modal.Post
import com.nameisjayant.kmpapp.jsonplaceholder.data.network.PostApiService
import com.nameisjayant.kmpapp.jsonplaceholder.feature.ui.viewmodel.events.PostEvent
import com.nameisjayant.kmpapp.jsonplaceholder.feature.ui.viewmodel.states.PostStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(
    private val postApiService: PostApiService
) : ViewModel() {
    private val _postEventFlow: MutableStateFlow<PostStates<Post>> = MutableStateFlow(PostStates())
    var postEventFlow = _postEventFlow.asStateFlow()
        private set

    fun onEvent(event: PostEvent) = viewModelScope.launch {
        when (event) {
            PostEvent.GetPostEvent -> {
                _postEventFlow.value = PostStates(isLoading = true)
                try {
                    val data = postApiService.getPosts()
                    _postEventFlow.value = PostStates(
                        data = data
                    )
                } catch (e: Exception) {
                    _postEventFlow.value = PostStates(
                        error = "${e.message}"
                    )
                }
            }
        }
    }
}