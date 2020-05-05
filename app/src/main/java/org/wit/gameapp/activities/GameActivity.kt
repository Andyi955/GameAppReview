package org.wit.gameapp.activities


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.game_activity.*
import kotlinx.android.synthetic.main.game_list_activity.*
import okhttp3.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.gameapp.R
import org.wit.gameapp.helpers.readImage
import org.wit.gameapp.helpers.readImageFromPath
import org.wit.gameapp.helpers.showImagePicker
import org.wit.gameapp.main.MainApp
import org.wit.gameapp.models.GameModel
import java.io.IOException

/**
 * This Activity allows me to
 * add and edit Games using
 * text edits.
 *
 * Allows to upload and change image
 *
 *
 *
 *
 *
 */


class GameActivity : AppCompatActivity(), AnkoLogger {

    var game = GameModel()
    lateinit var app: MainApp
    val IMAGE_REQUEST = 1
    var edit = false






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity) // launches game activity






        app = application as MainApp

  //enables to toolbar
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)



   //uses intent when the game is pressed and it can be edited
        if (intent.hasExtra("game_edit")) {
            edit = true

            //allows game to be edited
            game = intent.extras.getParcelable<GameModel>("game_edit")
            gameTitle.setText(game.title)
            comment.setText(game.comment)
            genre.setText(game.genre)
            btnAdd.setText(R.string.save_game)
            gameImage.setImageBitmap(readImageFromPath(this, game.image))
            if (game.image != null) {
                chooseImage.setText(R.string.change_game_image)
            }
        }

        //adds a game when the button is pressed
        btnAdd.setOnClickListener() {
            game.title = gameTitle.text.toString()
            game.comment = comment.text.toString()
            game.genre = genre.text.toString()
            if (game.title.isEmpty()) {
                toast(R.string.enter_game_title)
            } else {
                if (edit) {
                    app.games.update(game.copy())
                } else {
                    app.games.create(game.copy())
                }
            }
            info("add Button Pressed: $gameTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        //allows for image to be pick from library
        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }



    }






    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)


        return super.onCreateOptionsMenu(menu)
    }

    //the allows the delete and cancel icon to be functional
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
    app.games.delete(game) //deletes the game that you have selected
        finish()
    }
    R.id.item_cancel -> {
        finish()  //brings you back to the home screen
    }

}

return super.onOptionsItemSelected(item)
    }



    //gets the game image and saves it
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    game.image = data.getData().toString()
                    gameImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_game_image)

                }
            }

        }
    }


}






