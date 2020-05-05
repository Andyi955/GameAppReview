package org.wit.gameapp.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

//get id
internal fun getId(): Long {
    return lastId++
}

class GameMemReview : GameReview, AnkoLogger {
    val games = ArrayList<GameModel>()

    //finds all games in the list and returns them
    override fun findAll(): List<GameModel>{
        return games
    }

    //creates a game
    override fun create(game: GameModel){
        game.id = getId()
        games.add(game)
       logAll()

    }

    override fun update(game: GameModel) {
        var foundGame: GameModel? = games.find { p -> p.id == game.id }
        if (foundGame != null) {
            foundGame.title = game.title
            foundGame.comment = game.comment
            foundGame.genre = game.genre
            foundGame.image = game.image

            logAll()
        }
    }
    override fun delete(game: GameModel) {
        games.remove(game)
    }
        fun logAll(){
            games.forEach{info("${it}")}
        }
    }
