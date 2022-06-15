package com.khedr.shoeshop.domain.useCases

class ValidatePasswordUseCase {
    operator fun invoke(password: String?) = password.isNullOrBlank().not()
}