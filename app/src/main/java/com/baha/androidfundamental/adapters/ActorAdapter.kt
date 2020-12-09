package com.baha.androidfundamental.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baha.androidfundamental.R
import com.baha.androidfundamental.data.Actor

class ActorAdapter:RecyclerView.Adapter<ActorsViewHolder>() {

    private var actors = listOf<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actors, parent, false)
        return ActorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int = actors.size
    fun bindActors(newActor: List<Actor>) {
        actors = newActor
        notifyDataSetChanged()
    }
}


class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val actorName: TextView = itemView.findViewById(R.id.tvActorName)
    private val actorPhoto: ImageView = itemView.findViewById(R.id.ivActorPhoto)

    fun bind(actor: Actor) {
        actorName.text = actor.actorName
        actorPhoto.setImageResource(actor.actorPhoto)
    }

}