package com.ethermail.androidchallenge.data


import com.ethermail.androidchallenge.data.model.assets.AssetsApiData
import com.ethermail.androidchallenge.data.model.markets.MarketsApiData
import retrofit2.http.GET
import retrofit2.http.Query

const val HOST_COIN_CAP = "https://api.coincap.io/"

interface CoinCapService {

    @GET("/v2/assets")
    suspend fun getAssets(): AssetsApiData

    @GET("/v2/markets")
    suspend fun getMarkets(@Query("baseId") baseId: String): MarketsApiData
}