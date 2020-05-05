package org.wit.gameapp.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.game_cards.view.*
import org.wit.gameapp.R
import org.wit.gameapp.helpers.readImageFromPath
import org.wit.gameapp.models.GameModel


interface GameListener{
    fun onGameClick(game: GameModel)
}

class GameAdapter constructor(private var games: List<GameModel>,
                              private val listener: GameListener) :  RecyclerView.Adapter<GameAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder{
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.game_cards,parent,false))
           }


    override fun onBindViewHolder(holder: MainHolder,position: Int){
        val game = games[holder.adapterPosition]
        holder.bind(game, listener)
    }

    override fun getItemCount(): Int {

        return games.size}

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(game: GameModel, listener: GameListener){
            itemView.gameTitles.text = game.title
            itemView.comments.text = game.comment
            itemView.genres.text = game.genre
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context,game.image))
            itemView.setOnClickListener{listener.onGameClick(game)}
        }
    }



}