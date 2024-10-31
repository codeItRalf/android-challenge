package com.ethermail.androidchallenge.ui.theme.features.markets

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethermail.androidchallenge.data.repository.MarketsRepository
import com.ethermail.androidchallenge.ui.theme.features.markets.MarketMapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val marketsRepository: MarketsRepository
) : ViewModel() {
    private val _market = MutableStateFlow<MarketUiItem?>(null)
    val market : StateFlow<MarketUiItem?> = _market.asStateFlow()

    private  val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private  val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadMarketWithHighestVolume(baseId: String) {
        viewModelScope.launch {
            _error.value = null
            _isLoading.value = true
            try {
                val response = marketsRepository.getMarkets(baseId)
                // Find the market with the highest volume
                val highestVolumeMarket = response.marketData
                    ?.maxByOrNull {
                        try {
                            it.volumeUsd24Hr?.toDouble() ?: 0.0
                        } catch (e: Exception) {
                            Log.e("MarketsViewModel", "Error parsing volume: ${it.volumeUsd24Hr}", e)
                            0.0
                        }
                    }
                    ?.toUiModel()
                _market.value = highestVolumeMarket
            } catch (e: Exception) {
                Log.e("MarketsViewModel", "Error fetching markets", e)
                _error.value = e.message ?: "Unknown error occurred"
            }finally {
                _isLoading.value = false
            }
        }

    }

}