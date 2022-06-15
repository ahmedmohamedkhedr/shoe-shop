package com.khedr.shoeshop.domain.useCases

import com.khedr.shoeshop.data.PreferencesRepository
import com.khedr.shoeshop.domain.models.UserModel
import javax.inject.Inject

class SetCurrentUserUseCase @Inject constructor(private val repository: PreferencesRepository) {
    operator fun invoke(user: UserModel?) = repository.setCurrentUser(user)
}