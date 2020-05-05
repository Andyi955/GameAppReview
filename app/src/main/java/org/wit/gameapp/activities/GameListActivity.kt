package org.wit.gameapp.activities



import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.game_list_activity.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.gameapp.R
import org.wit.gameapp.adapter.GameAdapter
import org.wit.gameapp.adapter.GameListener
import org.wit.gameapp.main.MainApp
import org.wit.gameapp.models.GameModel
import android.view.MenuItem as MenuItem
/**
 * {HomePage}
 * This is where my games will be listed
 * It will display the game cards
 * It will have the main menu icons
 *
 * Will use the GameAdapter
 * **/

class GameListActivity : AppCompatActivity() ,
    GameListener {

    lateinit var app: MainApp



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_list_activity)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadGames()
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)





    }

    /**
     * Allows game card to be selected then brought
     * back to the game activity menu allowing it to be edited
     */
    override fun onGameClick(game: GameModel) {
        startActivityForResult(intentFor<GameActivity>().putExtra("game_edit", game), 0)
    }

    /**
     * inflates the main menu
     * displaying the 3 different icons
     * Also has a search function that can't
     * search.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.menu_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("",false)
                searchItem.collapseActionView()
                loadGames()
                Toast.makeText(this@GameListActivity,"Looking for $query", Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                Toast.makeText(this@GameListActivity,"Looking for $newText", Toast.LENGTH_LONG).show()
                return false
            }

        })

            return super.onCreateOptionsMenu(menu)

    }


    /**
     * When the menu icons are pressed this
     * will redirect them to the Activity they
     * are designated
     */
    override fun onOptionsItemSelected(item: MenuItem ?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<GameActivity>(0)//start GameActivity

            R.id.api_screen -> startActivityForResult<ApiActivity>(0)//starts ApiActivity

        }
        return super.onOptionsItemSelected(item)
    }


    /**
     * This loads the games when the app starts
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadGames()
        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * Uses the Game Review Interface
     * method find all()
     */
    private fun loadGames() {
        showGames(app.games.findAll())
    }

    /**
     * This uses GameModel as a list
     * then uses the recycler view to display
     * whats in the adapters
     *
     */
    fun showGames (games: List<GameModel>) {
        recyclerView.adapter = GameAdapter(games, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }



}


