package org.wit.gameapp.models


import com.google.gson.annotations.SerializedName

/**
 * Main model used to get api info
 * about developers
 */

data class Result(
    @SerializedName("games")
    val games: List<Game>,
    @SerializedName("games_count")
    val gamesCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_background")
    val imageBackground: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)