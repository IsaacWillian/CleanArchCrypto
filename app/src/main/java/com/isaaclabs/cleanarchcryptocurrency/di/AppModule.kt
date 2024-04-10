package com.isaaclabs.cleanarchcryptocurrency.di

import com.isaaclabs.cleanarchcryptocurrency.common.Constants
import com.isaaclabs.cleanarchcryptocurrency.data.remote.CoinPaprikaApi
import com.isaaclabs.cleanarchcryptocurrency.data.repository.CoinRepositoryImpl
import com.isaaclabs.cleanarchcryptocurrency.domain.repository.CoinRepository
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
    fun providePaprikaApi(): CoinPaprikaApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository{
        return CoinRepositoryImpl(api)
    }
}