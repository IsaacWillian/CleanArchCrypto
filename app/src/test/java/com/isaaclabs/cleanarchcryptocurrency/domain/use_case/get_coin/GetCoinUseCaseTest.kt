package com.isaaclabs.cleanarchcryptocurrency.domain.use_case.get_coin

import app.cash.turbine.test
import com.isaaclabs.cleanarchcryptocurrency.common.Resource
import com.isaaclabs.cleanarchcryptocurrency.data.Mocks
import com.isaaclabs.cleanarchcryptocurrency.domain.repository.CoinRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetCoinUseCaseTest{

    private val coinRepository = mockk<CoinRepository>()
    private val getCoinUseCase = GetCoinUseCase(coinRepository)

    @Test
    fun `getCoinsUseCase need emit loading and data`() = runTest{
        //Given - Success repository call
        coEvery { coinRepository.getCoinById(any()) } returns Resource.Success(Mocks.mockCoinDetail)

        //Then - call getCoinsUseCase
        val flow = getCoinUseCase("")


        //When - emit loading and data
        flow.test {
            Assert.assertTrue(awaitItem() is Resource.Loading)
            val item = awaitItem()
            Assert.assertTrue(item is Resource.Success)
            Assert.assertEquals(item.data, Mocks.mockCoinDetail)
            awaitComplete()
        }
    }

    @Test
    fun `getCoinsUseCase need set loading and Error`() = runTest{
        //Given - Success repository call
        coEvery { coinRepository.getCoinById(any()) } returns Resource.Error("error message")

        //Then - call getCoinsUseCase
        val flow = getCoinUseCase("")


        //When - emit loading and data
        flow.test {
            Assert.assertTrue(awaitItem() is Resource.Loading)
            val item = awaitItem()
            Assert.assertTrue(item is Resource.Error)
            Assert.assertEquals(item.message,"error message")
            awaitComplete()
        }
    }
}