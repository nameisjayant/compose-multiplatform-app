package com.nameisjayant.kmpapp.api.feature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nameisjayant.kmpapp.api.data.modal.Post
import com.nameisjayant.kmpapp.api.data.network.PostApiService
import com.nameisjayant.kmpapp.api.feature.ui.viewmodel.events.PostEvent
import com.nameisjayant.kmpapp.api.feature.ui.viewmodel.states.PostStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(
    private val postApiService: PostApiService
) : ViewModel() {
    private val _postEventFlow: MutableStateFlow<PostStates<Post>> = MutableStateFlow(PostStates())
    var postEventFlow = _postEventFlow.asStateFlow()
        private set

    private val _postData: MutableStateFlow<Post?> = MutableStateFlow(null)
    var postData = _postData.asStateFlow()
        private set

    fun setPostData(data: Post) {
        _postData.value = data
    }

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