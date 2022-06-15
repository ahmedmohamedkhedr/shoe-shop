package com.khedr.shoeshop.domain.useCases

import android.text.TextUtils
import android.util.Patterns

class ValidateEmailUseCase {
    operator fun invoke(email: String) =
        (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
}