package com.nameisjayant.kmpapp.jsonplaceholder.feature.ui.viewmodel.events

sealed class PostEvent {

    data object GetPostEvent : PostEvent()

}