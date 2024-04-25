package com.nameisjayant.kmpapp.api.feature.ui.viewmodel.events

sealed class PostEvent {

    data object GetPostEvent : PostEvent()

}