package com.isaaclabs.cleanarchcryptocurrency.domain.use_case.get_coins

import app.cash.turbine.test
import com.isaaclabs.cleanarchcryptocurrency.common.Resource
import com.isaaclabs.cleanarchcryptocurrency.data.Mocks
import com.isaaclabs.cleanarchcryptocurrency.domain.repository.CoinRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test


class GetCoinsUseCaseTest{

    private val coinRepository = mockk<CoinRepository>()
    private val getCoinsUseCase = GetCoinsUseCase(coinRepository)

    @Test
    fun `getCoinsUseCase need emit loading and data`() = runTest{
        //Given - Success repository call
        coEvery { coinRepository.getCoins() } returns Resource.Success(Mocks.coins)

        //Then - call getCoinsUseCase
        val flow = getCoinsUseCase()


        //When - emit loading and data
        flow.test {
            assertTrue(awaitItem() is Resource.Loading)
            val item = awaitItem()
            assertTrue(item is Resource.Success)
            assertEquals(item.data,Mocks.coins)
            awaitComplete()
        }
    }

    @Test
    fun `getCoinsUseCase need emit loading and Error`() = runTest{
        //Given - Success repository call
        coEvery { coinRepository.getCoins() } returns Resource.Error("error message")

        //Then - call getCoinsUseCase
        val flow = getCoinsUseCase()


        //When - emit loading and data
        flow.test {
            assertTrue(awaitItem() is Resource.Loading)
            val item = awaitItem()
            assertTrue(item is Resource.Error)
            assertEquals(item.message,"error message")
            awaitComplete()
        }
    }

}