package com.code_wizards.ecology.hilt

import android.content.Context
import com.code_wizards.ecology.network.interfaceApi
import com.code_wizards.ecology.repository.PurchaseRepository
import com.code_wizards.ecology.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideiApi(): interfaceApi{
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(interfaceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: interfaceApi): UserRepository{
        return UserRepository(api)
    }

    @Provides
    @Singleton
    fun providePurchaseRepository(api: interfaceApi): PurchaseRepository {
        return PurchaseRepository(api)
    }
}