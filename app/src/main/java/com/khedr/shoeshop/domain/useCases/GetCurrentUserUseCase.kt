package com.khedr.shoeshop.domain.useCases

import com.khedr.shoeshop.data.PreferencesRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val repository: PreferencesRepository) {
    operator fun invoke() = repository.getCurrentUser()
}