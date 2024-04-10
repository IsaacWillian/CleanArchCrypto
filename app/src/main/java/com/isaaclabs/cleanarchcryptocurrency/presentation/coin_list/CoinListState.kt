package com.isaaclabs.cleanarchcryptocurrency.presentation.coin_list

import com.isaaclabs.cleanarchcryptocurrency.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = "",
)
