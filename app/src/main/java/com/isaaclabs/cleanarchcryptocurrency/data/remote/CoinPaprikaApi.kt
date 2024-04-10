package com.isaaclabs.cleanarchcryptocurrency.data.remote

import com.isaaclabs.cleanarchcryptocurrency.data.remote.dto.CoinDetailDto
import com.isaaclabs.cleanarchcryptocurrency.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinDetail(@Path("coinId") coinId:String): CoinDetailDto
}