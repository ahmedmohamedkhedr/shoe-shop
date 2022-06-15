package com.khedr.shoeshop.data

import android.content.Context
import com.khedr.shoeshop.domain.models.UserModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class PreferencesRepositoryImp(private val context: Context) : PreferencesRepository {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val jsonAdapter = moshi.adapter(UserModel::class.java).lenient()
    private val preferences = EncryptedPreferences(context)

    companion object {
        const val ARG_USER = "ARG_USER"
    }

    override fun setCurrentUser(userModel: UserModel?) {
        preferences.edit().putString(ARG_USER, jsonAdapter.toJson(userModel)).apply()
    }

    override fun getCurrentUser(): UserModel? {
        return jsonAdapter.fromJson(preferences.getString(ARG_USER, null).toString())
    }


}