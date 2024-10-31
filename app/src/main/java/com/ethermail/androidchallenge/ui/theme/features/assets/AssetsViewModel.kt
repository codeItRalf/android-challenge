package com.ethermail.androidchallenge.ui.theme.features.assets

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethermail.androidchallenge.data.repository.AssetsRepository
import com.ethermail.androidchallenge.ui.theme.features.assets.AssetsMapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssetsViewModel @Inject constructor(
    private val repository: AssetsRepository,
) : ViewModel() {

    private val _assets = MutableStateFlow<List<AssetUiItem>>(emptyList())
    val assets: StateFlow<List<AssetUiItem>> = _assets.asStateFlow()

    private  val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private  val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()


     fun loadAssets() {
         viewModelScope.launch {
             try {
                 _error.value = null
                 _isLoading.value = true
                 val response = repository.getAssets()
                 _assets.value = response.assetData?.map { it.toUiModel() } ?: emptyList()
             }catch (e : Exception) {
                 _error.value = e.message ?: "Unknown error occurred"
                 Log.e("AssetsViewModel", "Error fetching assets", e)
             }finally {
                 _isLoading.value = false
             }

         }
    }
}