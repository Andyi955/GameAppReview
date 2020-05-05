package org.wit.gameapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.api_cards.view.*
import org.wit.gameapp.R
import org.wit.gameapp.activities.MyHolder
import org.wit.gameapp.models.Game
import org.wit.gameapp.models.Result


/**
 * This the API adapter
 * It will allow me to display
 * the values from the result model
 * for the recycler view
 *
 *
 */

class ApiAdapter(private val dataList: MutableList<Result>) : RecyclerView.Adapter<MyHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolder {
        context = parent.context
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.api_cards,parent,false)) // opens api_cards
    }

    override fun getItemCount(): Int {
        return dataList.size} // gets the size of result model



    override fun onBindViewHolder(holder: MyHolder, p1: Int) {

        val data = dataList[p1]

        val GameName = holder.itemView.GameTitleApi //id from the xml file api_cards gets the titles
        val apiImage = holder.itemView.imageView    //id from the xml file api_cards gets the image
        val counted = holder.itemView.counts

        val gname = "${data.name} "
        val amount = "${data.gamesCount}"
        GameName.text = gname //allows the values from result api to be displayed onto the cards
        counted.text = amount
        Picasso.get() //
            .load(data.imageBackground) //displays background image
            .into(apiImage)

        holder.itemView.setOnClickListener{
            Toast.makeText(context, gname,Toast.LENGTH_SHORT).show() //displays the name of the
        }

    }
}