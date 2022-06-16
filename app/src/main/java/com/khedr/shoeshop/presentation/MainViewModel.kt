package com.khedr.shoeshop.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khedr.shoeshop.domain.models.ShoeModel
import com.khedr.shoeshop.domain.models.UserModel
import com.khedr.shoeshop.domain.useCases.GetCurrentUserUseCase
import com.khedr.shoeshop.domain.useCases.SetCurrentUserUseCase
import com.khedr.shoeshop.domain.useCases.ValidateEmailUseCase
import com.khedr.shoeshop.domain.useCases.ValidatePasswordUseCase
import com.khedr.shoeshop.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class Interactor @Inject constructor(
    val setCurrentUserUseCase: SetCurrentUserUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val getCurrentUserUseCase: GetCurrentUserUseCase
)

@HiltViewModel
class MainViewModel @Inject constructor(private val interactor: Interactor) : ViewModel() {
    val loginSuccessLiveData: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val currentUserLiveData = SingleLiveEvent<UserModel?>()
    val onLogoutLiveData = SingleLiveEvent<Unit>()
    val newShoeLiveData = MutableLiveData<MutableList<ShoeModel>>()
    private val shoesList = mutableListOf<ShoeModel>()

     val shoeNameLiveData = MutableLiveData<String>()
     val shoeCompanyLiveData = MutableLiveData<String>()
     val shoeSizeLiveData = MutableLiveData<String>()
     val shoeDescLiveData = MutableLiveData<String>()


    fun login(email: String, password: String) {
        interactor.setCurrentUserUseCase(UserModel(email = email, password = password))
        loginSuccessLiveData.postValue(true)
    }

    fun logout() {
        interactor.setCurrentUserUseCase(null)
        onLogoutLiveData.postValue(Unit)
    }

    fun getCurrentUser() {
        val user = interactor.getCurrentUserUseCase()
        currentUserLiveData.postValue(user)
    }

    fun isEmailValid(email: String) = interactor.validateEmailUseCase(email)

    fun isPasswordValid(password: String) = interactor.validatePasswordUseCase(password)

    fun addNewShoe() {
        shoesList.add(
            ShoeModel(
                shoeNameLiveData.value,
                shoeCompanyLiveData.value,
                shoeSizeLiveData.value,
                shoeDescLiveData.value
            )
        )
        newShoeLiveData.postValue(shoesList)
    }
}