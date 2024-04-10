package com.isaaclabs.cleanarchcryptocurrency.presentation.coin_detail

import com.isaaclabs.cleanarchcryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = "",
)
