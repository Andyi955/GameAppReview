package org.wit.gameapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener

import kotlinx.android.synthetic.main.recyclerview_adapter.*
import kotlinx.android.synthetic.main.recyclerview_adapter.toolbarMain
import org.wit.gameapp.R
import org.wit.gameapp.adapter.ApiAdapter
import org.wit.gameapp.models.GameListApi
import org.wit.gameapp.models.Result

/**
 * This is my Api class it will have
 * the methods i need to vew
 *
 *
 *
 *
 *
 */


class ApiActivity : AppCompatActivity() {


         //dataList is Getting a mutable list of Resukt
    private val dataList:MutableList<Result> = mutableListOf()


     //apiAdapter is used to call methods for APiAdapter
    lateinit var apiAdapter: ApiAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_adapter)

        //
        apiAdapter = ApiAdapter(dataList)

        //setup recyclerview

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.addItemDecoration(DividerItemDecoration(this,OrientationHelper.VERTICAL))
        recyclerview.adapter = apiAdapter


              AndroidNetworking.initialize(this)
           //Android
              AndroidNetworking.get("https://api.rawg.io/api/developers") //gets my api


            .build()
                  .getAsObject(GameListApi::class.java,object : ParsedRequestListener<GameListApi>{
                      override fun onResponse(response: GameListApi) {
                          dataList.addAll(response.results)
                          apiAdapter.notifyDataSetChanged()
                      }

                      override fun onError(anError: ANError?) {

                      }


                  })

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_api, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }

        }

        return super.onOptionsItemSelected(item)
    }

}

