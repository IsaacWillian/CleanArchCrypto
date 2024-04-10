package com.isaaclabs.cleanarchcryptocurrency.domain.use_case.get_coins

import com.isaaclabs.cleanarchcryptocurrency.common.Resource
import com.isaaclabs.cleanarchcryptocurrency.domain.model.Coin
import com.isaaclabs.cleanarchcryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow{
        emit(Resource.Loading())
        val result = repository.getCoins()
        emit(result)
    }
}