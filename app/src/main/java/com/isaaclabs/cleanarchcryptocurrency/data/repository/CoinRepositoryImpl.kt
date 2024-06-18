package com.isaaclabs.cleanarchcryptocurrency.data.repository

import android.net.http.HttpException
import com.isaaclabs.cleanarchcryptocurrency.common.Resource
import com.isaaclabs.cleanarchcryptocurrency.data.remote.CoinPaprikaApi
import com.isaaclabs.cleanarchcryptocurrency.data.remote.dto.toCoin
import com.isaaclabs.cleanarchcryptocurrency.data.remote.dto.toCoinDetail
import com.isaaclabs.cleanarchcryptocurrency.domain.model.Coin
import com.isaaclabs.cleanarchcryptocurrency.domain.model.CoinDetail
import com.isaaclabs.cleanarchcryptocurrency.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): Resource<List<Coin>> {
        try {
            val coins = api.getCoins().map { it.toCoin() }
            return Resource.Success(coins)
        } catch(e: retrofit2.HttpException){
            return Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            return Resource.Error("Couldn't reach server, Check your internet connection")
        }
    }

    override suspend fun getCoinById(coinId: String): Resource<CoinDetail> {
        try {
            val coin = api.getCoinDetail(coinId).toCoinDetail()
            return Resource.Success(coin)
        } catch(e: retrofit2.HttpException){
            return Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException){
            return Resource.Error("Couldn't reach server, Check your internet connection")
        }
    }
}