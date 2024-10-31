package com.ethermail.androidchallenge.ui.features.markets

import app.cash.turbine.turbineScope
import com.ethermail.androidchallenge.data.CoinCapService
import com.ethermail.androidchallenge.data.model.assets.AssetData
import com.ethermail.androidchallenge.data.model.assets.AssetsApiData
import com.ethermail.androidchallenge.data.model.markets.MarketData
import com.ethermail.androidchallenge.data.model.markets.MarketsApiData
import com.ethermail.androidchallenge.data.repository.AssetsRepository
import com.ethermail.androidchallenge.data.repository.MarketsRepository
import com.ethermail.androidchallenge.ui.theme.features.assets.AssetUiItem
import com.ethermail.androidchallenge.ui.theme.features.assets.AssetsViewModel
import com.ethermail.androidchallenge.ui.theme.features.markets.MarketViewModel
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

class MarketsViewModelTest {
    private lateinit var viewModel: MarketViewModel
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
                return AssetsApiData(emptyList(), 0)
            }

            override suspend fun getMarkets(assetId: String): MarketsApiData {
                return MarketsApiData(
                    marketData = listOf(
                        MarketData(
                            exchangeId = "binance",
                            baseId = assetId,
                            volumeUsd24Hr = "100.0",
                            priceUsd = "50000",
                            updated = System.currentTimeMillis(),
                            quoteId = "usd",
                            rank = "2",
                            priceQuote = "50000",
                            quoteSymbol = "USD",
                            percentExchangeVolume = "10",
                            tradesCount24Hr = "1000",
                            baseSymbol = "BTC",
                        ),
                        MarketData(
                            exchangeId = "coinbase",
                            baseId = assetId,
                            volumeUsd24Hr = "300.0",
                            priceUsd = "50000",
                            updated = System.currentTimeMillis(),
                            quoteId = "usd",
                            rank = "1",
                            priceQuote = "50000",
                            quoteSymbol = "USD",
                            percentExchangeVolume = "10",
                            tradesCount24Hr = "1000",
                            baseSymbol = "BTC",
                        ),
                        MarketData(
                            exchangeId = "MockExchange",
                            baseId = assetId,
                            volumeUsd24Hr = "200.0",
                            priceUsd = "50000",
                            updated = System.currentTimeMillis(),
                            quoteId = "usd",
                            rank = "1",
                            priceQuote = "50000",
                            quoteSymbol = "USD",
                            percentExchangeVolume = "20",
                            tradesCount24Hr = "2000",
                            baseSymbol = "BTC",
                        ),

                    ),

                    timestamp = System.currentTimeMillis()
                )
            }
        }
        val repository = MarketsRepository(fakeService, dispatcher = testDispatcher)
        viewModel = MarketViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // Reset the main dispatcher
        Dispatchers.resetMain()
    }


    @Test
    fun `Check that market with highest volume gets filtered`() = runTest {
        turbineScope{
            val flowTurbine = viewModel.market.testIn(this)

            val initialState = flowTurbine.awaitItem()

            assertEquals(null, initialState)


            viewModel.loadMarketWithHighestVolume("bitcoin")

            val loadingState = flowTurbine.awaitItem()
            println("Check that correct market with highest volume gets filtered")
            assert(loadingState != null)
            assert(loadingState?.exchangeId == "coinbase")
            println("Test Success: Check that correct market with highest volume gets filtered")
            flowTurbine.cancelAndIgnoreRemainingEvents()

        }

    }

}