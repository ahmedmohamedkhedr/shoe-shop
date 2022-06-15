package com.khedr.shoeshop.di

import android.content.Context
import com.khedr.shoeshop.data.PreferencesRepository
import com.khedr.shoeshop.data.PreferencesRepositoryImp
import com.khedr.shoeshop.domain.useCases.GetCurrentUserUseCase
import com.khedr.shoeshop.domain.useCases.SetCurrentUserUseCase
import com.khedr.shoeshop.domain.useCases.ValidateEmailUseCase
import com.khedr.shoeshop.domain.useCases.ValidatePasswordUseCase
import com.khedr.shoeshop.presentation.Interactor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppBinder {
    @Binds
    abstract fun bindRepository(imp: PreferencesRepositoryImp): PreferencesRepository
}

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providePreferencesRepository(@ApplicationContext context: Context) =
        PreferencesRepositoryImp(context)

    @Provides
    @Singleton
    fun provideSetCurrentUserUseCase(repository: PreferencesRepository) =
        SetCurrentUserUseCase(repository)

    @Provides
    @Singleton
    fun provideValidateEmailUseCase() = ValidateEmailUseCase()

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase() = ValidatePasswordUseCase()

    @Provides
    @Singleton
    fun provideGetCurrentUserUseCase(repository: PreferencesRepository) =
        GetCurrentUserUseCase(repository)

    @Provides
    @Singleton
    fun provideInteractor(
        setCurrentUseCase: SetCurrentUserUseCase,
        validateEmailUseCase: ValidateEmailUseCase,
        validatePasswordUseCase: ValidatePasswordUseCase,
        getCurrentUserUseCase: GetCurrentUserUseCase
    ) = Interactor(
        setCurrentUseCase,
        validateEmailUseCase,
        validatePasswordUseCase,
        getCurrentUserUseCase
    )
}