package com.isaaclabs.cleanarchcryptocurrency.domain.repository

import com.isaaclabs.cleanarchcryptocurrency.common.Resource
import com.isaaclabs.cleanarchcryptocurrency.domain.model.Coin
import com.isaaclabs.cleanarchcryptocurrency.domain.model.CoinDetail

interface CoinRepository {
    suspend fun getCoins(): Resource<List<Coin>>
    suspend fun getCoinById(coinId: String): Resource<CoinDetail>
}