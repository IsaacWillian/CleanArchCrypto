package com.isaaclabs.cleanarchcryptocurrency.data.repository

import com.isaaclabs.cleanarchcryptocurrency.common.Resource
import com.isaaclabs.cleanarchcryptocurrency.data.Mocks
import com.isaaclabs.cleanarchcryptocurrency.data.remote.CoinPaprikaApi
import com.isaaclabs.cleanarchcryptocurrency.data.remote.dto.CoinDto
import com.isaaclabs.cleanarchcryptocurrency.domain.model.Coin
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.io.IOException


class CoinRepositoryImplTest{


    private val paprikaApi = mockk<CoinPaprikaApi>()
    private val coinRepositoryImpl = CoinRepositoryImpl(paprikaApi)

    @Test
    fun `getCoins return success`() = runTest {
        //Given - Successfully api call
        val coinsDto =  listOf(CoinDto("1",true,true,"Test",1,"TST","test"))
        val coins = listOf(Coin("1",true,"Test",1,"TST"))
        coEvery { paprikaApi.getCoins() } returns coinsDto

        //When - call getCoins
        val result = coinRepositoryImpl.getCoins()

        //Then - Return List of coins
        assertTrue(result is Resource.Success)
        assertEquals(coinsDto.size,result.data?.size)
        assertEquals(coins[0],result.data?.get(0))

    }

    @Test
    fun `getCoins return HttpException`() = runTest {
        //Given - HttpException in api Call
        val exception = mockk<retrofit2.HttpException>()
        coEvery { paprikaApi.getCoins() } throws exception
        every { exception.localizedMessage } returns "error message"

        //When - call get coins
        val result = coinRepositoryImpl.getCoins()


        //Then - return error
        assertTrue(result is Resource.Error)
        assertEquals(result.message,"error message")
    }

    @Test
    fun `getCoins return IOException`() = runTest {
        //Given - HttpException in api Call
        val exception = mockk<IOException>()
        coEvery { paprikaApi.getCoins() } throws exception

        //When - call get coins
        val result = coinRepositoryImpl.getCoins()

        //Then - return error
        assertTrue(result is Resource.Error)
        assertEquals(result.message,"Couldn't reach server, Check your internet connection")
    }

    @Test
    fun `getCoinsById returns Success`() = runTest {
        //Given - Success Api call
        coEvery { paprikaApi.getCoinDetail(any()) } returns  Mocks.mockCoinDetailDto

        //When - call getCoinsById
        val result = coinRepositoryImpl.getCoinById(Mocks.mockCoinDetailDto.id)

        //Then - return success and correct data
        assertTrue(result is Resource.Success)
        assertEquals(Mocks.mockCoinDetail,result.data)
    }

    @Test
    fun `getCoinsByID return HttpException`() = runTest {
        //Given - HttpException in api Call
        val exception = mockk<retrofit2.HttpException>()
        coEvery { paprikaApi.getCoinDetail(any()) } throws exception
        every { exception.localizedMessage } returns "error message"

        //When - call get coins
        val result = coinRepositoryImpl.getCoinById("")


        //Then - return error
        assertTrue(result is Resource.Error)
        assertEquals(result.message,"error message")
    }

    @Test
    fun `getCoinsById return IOException`() = runTest {
        //Given - HttpException in api Call
        val exception = mockk<IOException>()
        coEvery { paprikaApi.getCoinDetail(any()) } throws exception

        //When - call get coins
        val result = coinRepositoryImpl.getCoinById("")

        //Then - return error
        assertTrue(result is Resource.Error)
        assertEquals(result.message,"Couldn't reach server, Check your internet connection")
    }





}