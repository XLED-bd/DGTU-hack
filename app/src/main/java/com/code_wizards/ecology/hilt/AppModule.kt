package com.code_wizards.ecology.hilt

import android.content.Context
import com.code_wizards.ecology.network.interfaceApi
import com.code_wizards.ecology.network.interfaceCompanyApi
import com.code_wizards.ecology.repository.ProfileRepository
import com.code_wizards.ecology.repository.PurchaseRepository
import com.code_wizards.ecology.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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
            .baseUrl("https://------")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(interfaceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCompanyiApi(): interfaceCompanyApi {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-Auth-Token", "12")
                    .build()
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://6ec5-176-59-81-233.ngrok-free.app")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        return retrofit.create(interfaceCompanyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: interfaceApi): UserRepository{
        return UserRepository(api)
    }


    @Provides
    @Singleton
    fun provideProfileRepository(api: interfaceApi): ProfileRepository {
        return ProfileRepository(api)
    }

    @Provides
    @Singleton
    fun providePurchaseRepository(api: interfaceCompanyApi): PurchaseRepository {
        return PurchaseRepository(api)
    }

}