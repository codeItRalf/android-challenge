package com.ethermail.androidchallenge.data.repository

import android.util.Log
import com.ethermail.androidchallenge.data.CoinCapService
import com.ethermail.androidchallenge.data.model.assets.AssetsApiData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AssetsRepository @Inject constructor(
    private val coinCapService: CoinCapService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
  suspend fun getAssets() : AssetsApiData {
    return  withContext(dispatcher) {
          try {
              coinCapService.getAssets()
          } catch (e: Exception) {
              Log.e("AssetsRepository", "Error fetching assets", e)
              throw e
          }
      }
  }

}