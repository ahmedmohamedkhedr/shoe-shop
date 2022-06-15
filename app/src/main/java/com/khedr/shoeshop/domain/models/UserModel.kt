package com.khedr.shoeshop.domain.models

import java.util.*


data class UserModel(
    val id: String = UUID.randomUUID().toString(),
    val email: String,
    val password: String
)
