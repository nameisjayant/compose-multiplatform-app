package com.nameisjayant.kmpapp.mesume.screens.detail

import cafe.adriel.voyager.core.model.ScreenModel
import com.nameisjayant.kmpapp.mesume.data.MuseumObject
import com.nameisjayant.kmpapp.mesume.data.MuseumRepository
import kotlinx.coroutines.flow.Flow

class DetailScreenModel(private val museumRepository: MuseumRepository) : ScreenModel {
    fun getObject(objectId: Int): Flow<MuseumObject?> =
        museumRepository.getObjectById(objectId)
}
