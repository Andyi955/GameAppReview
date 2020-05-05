package org.wit.gameapp.models

/**
 * Interface that hold the various methods
 *
 */

interface GameReview {
    fun findAll(): List<GameModel>
    fun create(game: GameModel)
    fun update(game: GameModel)
    fun delete(game: GameModel)
}
