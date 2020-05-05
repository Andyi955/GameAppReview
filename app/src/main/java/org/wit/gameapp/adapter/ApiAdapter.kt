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

class ApiAdapter(private val dataList: MutableList<Result>) : RecyclerView.Adapter<MyHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolder {
        context = parent.context
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.api_cards,parent,false))
    }

    override fun getItemCount(): Int {
        return dataList.size}



    override fun onBindViewHolder(holder: MyHolder, p1: Int) {

        val data = dataList[p1]

        val videoName = holder.itemView.GameTitleApi //id from the xml file api_cards gets the titles
        val apiImage = holder.itemView.imageView    //id from the xml file api_cards gets the image
        val released = holder.itemView.released      //gets the date

        val vidname = "${data.name} "
        val datereleased = "${data.gamesCount}"
        videoName.text = vidname
        released.text = datereleased
        Picasso.get()
            .load(data.imageBackground)
            .into(apiImage)

        holder.itemView.setOnClickListener{
            Toast.makeText(context, vidname,Toast.LENGTH_SHORT).show()
        }

    }
}