package com.ethermail.androidchallenge.data.repository

import com.ethermail.androidchallenge.data.CoinCapService
import com.ethermail.androidchallenge.data.model.markets.MarketsApiData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarketsRepository @Inject constructor(
    private val coinCapService: CoinCapService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend fun getMarkets(baseId: String): MarketsApiData {
     return   withContext(dispatcher) {
            try {
                coinCapService.getMarkets(baseId)
            } catch (e: Exception) {
                throw e
            }

        }
    }
}