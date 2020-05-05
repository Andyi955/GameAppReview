package org.wit.gameapp.main


import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.gameapp.models.GameJSONStore
import org.wit.gameapp.models.GameReview


class MainApp : Application(), AnkoLogger {

    lateinit var games: GameReview

    override fun onCreate() {
        super.onCreate()
        games = GameJSONStore(applicationContext)

        info("Game  started")

    }
}