package com.isaaclabs.cleanarchcryptocurrency.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import com.isaaclabs.cleanarchcryptocurrency.common.Resource
import com.isaaclabs.cleanarchcryptocurrency.data.Mocks
import com.isaaclabs.cleanarchcryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import io.mockk.awaits
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CoinDetailViewModelTest{

    private val getCoinUseCase = mockk<GetCoinUseCase>()

    private lateinit var coinDetailViewModel: CoinDetailViewModel
    private lateinit var stateHandler : SavedStateHandle


    @Before
    fun setup(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
        stateHandler = mockk<SavedStateHandle>()
    }

    @Test
    fun `getCoins set SuccessState`() = runTest{
        //Given - success call
        coEvery { getCoinUseCase.invoke(any()) } returns flowOf(Resource.Success(Mocks.mockCoinDetail))
        coEvery { stateHandler.get<String>(any()) } returns "0"
        //When - init viewModel
        coinDetailViewModel = CoinDetailViewModel(getCoinUseCase,stateHandler)

        //Then - state have coin list
        assertEquals(Mocks.mockCoinDetail,coinDetailViewModel.state.value.coin)
    }

    @Test
    fun `getCoins set ErrorState`() = runTest{
        //Given - success call
        coEvery { getCoinUseCase.invoke(any()) } returns flowOf(Resource.Error("error message"))
        every { stateHandler.get<String>(any()) } returns "0"
        //When - init viewModel
        coinDetailViewModel = CoinDetailViewModel(getCoinUseCase,stateHandler)

        //Then - state have coin list
        assertEquals(coinDetailViewModel.state.value.error,"error message")
    }

    @Test
    fun `getCoins set LoadingState`() = runTest{
        //Given - success call
        coEvery { getCoinUseCase.invoke(any()) } returns flowOf(Resource.Loading())
        every { stateHandler.get<String>(any()) } returns "0"
        //When - init viewModel
        coinDetailViewModel = CoinDetailViewModel(getCoinUseCase,stateHandler)

        //Then - state have coin list
        assertTrue(coinDetailViewModel.state.value.isLoading)
    }
}