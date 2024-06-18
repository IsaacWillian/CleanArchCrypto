package com.isaaclabs.cleanarchcryptocurrency.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.isaaclabs.cleanarchcryptocurrency.domain.model.CoinDetail

data class CoinDetailDto(
    val description: String,
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    @SerializedName("started_at")
    val symbol: String,
    val tags: List<Tag>?,
    val team: List<TeamMember>,
)

fun CoinDetailDto.toCoinDetail() = CoinDetail(
    coinId = id,
    name = name,
    description = description,
    symbol = symbol,
    rank = rank,
    isActive = isActive,
    tags = tags?.map { it.name } ?: emptyList(),
    team = team,
)