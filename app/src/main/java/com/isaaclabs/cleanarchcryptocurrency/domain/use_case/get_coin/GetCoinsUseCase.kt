package com.isaaclabs.cleanarchcryptocurrency.domain.use_case.get_coin

import com.isaaclabs.cleanarchcryptocurrency.common.Resource
import com.isaaclabs.cleanarchcryptocurrency.domain.model.Coin
import com.isaaclabs.cleanarchcryptocurrency.domain.model.CoinDetail
import com.isaaclabs.cleanarchcryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String ): Flow<Resource<CoinDetail>> = flow{
        emit(Resource.Loading())
        val result = repository.getCoinById(coinId)
        emit(result)
    }
}