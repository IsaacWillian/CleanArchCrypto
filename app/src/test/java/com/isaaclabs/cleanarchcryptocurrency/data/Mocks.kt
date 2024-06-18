package com.isaaclabs.cleanarchcryptocurrency.data

import com.isaaclabs.cleanarchcryptocurrency.data.remote.dto.CoinDetailDto
import com.isaaclabs.cleanarchcryptocurrency.data.remote.dto.CoinDto
import com.isaaclabs.cleanarchcryptocurrency.data.remote.dto.Tag
import com.isaaclabs.cleanarchcryptocurrency.data.remote.dto.TeamMember
import com.isaaclabs.cleanarchcryptocurrency.domain.model.Coin
import com.isaaclabs.cleanarchcryptocurrency.domain.model.CoinDetail

object Mocks {

    val coinsDto =  listOf(CoinDto("1",true,true,"Test",1,"TST","test"))
    val coins = listOf(Coin("1",true,"Test",1,"TST"))

    val mockTag = Tag(
        coinCounter = 123,
        icoCounter = 456,
        id = "tag-id",
        name = "Tag Name"
    )

    val mockTeamMember = TeamMember(
        id = "team-member-id",
        name = "Team Member Name",
        position = "Developer"
    )

    val mockCoinDetailDto = CoinDetailDto(
        description = "Descrição de exemplo",
        id = "bitcoin",
        isActive = true,
        name = "Bitcoin",
        rank = 1,
        symbol = "BTC",
        tags = listOf(mockTag),
        team = listOf(mockTeamMember)
    )

    val mockCoinDetail = CoinDetail(
        coinId = "bitcoin",
        name = "Bitcoin",
        description = "Descrição de exemplo",
        symbol = "BTC",
        rank = 1,
        isActive = true,
        tags = listOf("Tag Name"),
        team = listOf(mockTeamMember)
    )
}