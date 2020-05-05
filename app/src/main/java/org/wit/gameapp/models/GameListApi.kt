package org.wit.gameapp.models


import com.google.gson.annotations.SerializedName

/**
 * Main model for the api
 */

data class GameListApi(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)