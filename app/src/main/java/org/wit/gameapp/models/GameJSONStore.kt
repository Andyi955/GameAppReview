package org.wit.gameapp.models


import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.gameapp.helpers.*
import java.util.*


/**
 *
 * saves game to the json file
 *
 */


val JSON_FILE = "games.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<GameModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class GameJSONStore : GameReview, AnkoLogger {

    val context: Context
    var games = mutableListOf<GameModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<GameModel> {
        return games
    }

    override fun create(game: GameModel) {
        game.id = generateRandomId()
        games.add(game)
        serialize()
    }


    override fun update(game: GameModel) {
        var foundGame: GameModel? = games.find { p -> p.id == game.id }
        if (foundGame != null) {
            foundGame.title = game.title
            foundGame.comment = game.comment
            foundGame.genre = game.genre
            foundGame.image = game.image

            serialize()
        }
    }
    override fun delete(game: GameModel) {
        games.remove(game)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(games, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        games = Gson().fromJson(jsonString, listType)
    }
}