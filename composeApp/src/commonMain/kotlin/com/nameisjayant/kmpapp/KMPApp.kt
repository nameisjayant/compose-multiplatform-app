package com.nameisjayant.kmpapp

import com.nameisjayant.kmpapp.api.data.network.PostApiService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KMPApp : KoinComponent {

    val postApiService: PostApiService by inject()

}