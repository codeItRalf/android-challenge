package com.ethermail.androidchallenge.ui.features.assets

import app.cash.turbine.turbineScope
import com.ethermail.androidchallenge.data.CoinCapService
import com.ethermail.androidchallenge.data.model.assets.AssetData
import com.ethermail.androidchallenge.data.model.assets.AssetsApiData
import com.ethermail.androidchallenge.data.model.markets.MarketsApiData
import com.ethermail.androidchallenge.data.repository.AssetsRepository
import com.ethermail.androidchallenge.ui.theme.features.assets.AssetUiItem
import com.ethermail.androidchallenge.ui.theme.features.assets.AssetsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


class AssetsViewModelTest {
    private lateinit var viewModel: AssetsViewModel
    private lateinit var fakeService: CoinCapService

    @OptIn(ExperimentalCoroutinesApi::class)
    val testDispatcher = UnconfinedTestDispatcher()



    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        // Set the main dispatcher
        Dispatchers.setMain(testDispatcher)

        fakeService = object : CoinCapService {
            override suspend fun getAssets(): AssetsApiData {
                return AssetsApiData(listOf(
                    AssetData(
                        id = "bitcoin",
                        rank = "1",
                        symbol = "BTC",
                        name = "Bitcoin",
                        supply = "1000000",
                        maxSupply = "1000000",
                        marketCapUsd = "1000000",
                        volumeUsd24Hr = "1000000",
                        priceUsd = "50000",
                        changePercent24Hr = "0",
                        vwap24Hr = "0",
                        explorer = "https://blockchain.info/"
                    )
                ), 0)
            }

            override suspend fun getMarkets(): MarketsApiData {
                return MarketsApiData(
                    marketData = emptyList(),

                    timestamp = System.currentTimeMillis()
                )
            }
        }
        val repository = AssetsRepository(fakeService, dispatcher = testDispatcher)
        viewModel = AssetsViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // Reset the main dispatcher
        Dispatchers.resetMain()
    }


    @Test
    fun `test loadAssets`() = runTest {
        turbineScope{
            val flowTurbine = viewModel.assets.testIn(this)

            val initialState = flowTurbine.awaitItem()

            assertEquals(emptyList<AssetUiItem>(), initialState)

            viewModel.loadAssets()

            val loadingState = flowTurbine.awaitItem()
            // check list is not empty
            println("Check list is not empty")
            assert(loadingState.isNotEmpty())
            println("Test Success: Check list is not empty")
            flowTurbine.cancelAndIgnoreRemainingEvents()

        }

    }

}