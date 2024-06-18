package com.isaaclabs.cleanarchcryptocurrency.presentation.coin_list

import com.isaaclabs.cleanarchcryptocurrency.common.Resource
import com.isaaclabs.cleanarchcryptocurrency.data.Mocks
import com.isaaclabs.cleanarchcryptocurrency.domain.model.Coin
import com.isaaclabs.cleanarchcryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CoinListViewModelTest{

    private val getCoinsUseCase = mockk<GetCoinsUseCase>()

    private lateinit var coinListViewModel : CoinListViewModel


    @Before
    fun setup(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `getCoins set SuccessState`(){
        //Given - success call
        coEvery { getCoinsUseCase.invoke() } returns flowOf(Resource.Success(Mocks.coins))
        //When - init viewModel
        coinListViewModel = CoinListViewModel(getCoinsUseCase)

        //Then - state have coin list
        assertEquals(Mocks.coins,coinListViewModel.state.value.coins)

    }

    @Test
    fun `getCoins set SuccessState with null`(){
        //Given - success call
        coEvery { getCoinsUseCase.invoke() } returns flowOf(Resource.Success(Mocks.coins))
        //When - init viewModel
        coinListViewModel = CoinListViewModel(getCoinsUseCase)

        //Then - state have coin list
        assertEquals(Mocks.coins,coinListViewModel.state.value.coins)

    }

    @Test
    fun `getCoins set LoadingState`(){
        //Given - success call
        coEvery { getCoinsUseCase.invoke() } returns flowOf(Resource.Loading())
        //When - init viewModel
        coinListViewModel = CoinListViewModel(getCoinsUseCase)

        //Then - state have coin list
        assertTrue(coinListViewModel.state.value.isLoading)
    }

    @Test
    fun `getCoins set ErrorState`(){
        //Given - success call
        coEvery { getCoinsUseCase.invoke() } returns flowOf(Resource.Error("error message"))
        //When - init viewModel
        coinListViewModel = CoinListViewModel(getCoinsUseCase)

        //Then - state have coin list
        assertEquals(coinListViewModel.state.value.error,"error message")
    }

    @Test
    fun `getCoins set ErrorState with null`(){
        //Given - success call
        coEvery { getCoinsUseCase.invoke() } returns flowOf(Resource.Error("error message"))
        //When - init viewModel
        coinListViewModel = CoinListViewModel(getCoinsUseCase)

        //Then - state have coin list
        assertEquals(coinListViewModel.state.value.error,"error message")
    }

}