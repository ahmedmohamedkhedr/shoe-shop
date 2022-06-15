package com.khedr.shoeshop.data

import com.khedr.shoeshop.domain.models.UserModel

interface PreferencesRepository {
    fun setCurrentUser(userModel: UserModel?)
    fun getCurrentUser():UserModel?
}