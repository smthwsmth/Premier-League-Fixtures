package com.example.premierleaguefixtures

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MatchAdapter(
    private val matchesList: ArrayList<MatchItem>):
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

        var onItemClick: ((MatchItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)

        return MatchViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val currentItem = matchesList[position]
        holder.rvHomeTeam.text = currentItem.homeTeam
        holder.rvAwayTeam.text = currentItem.awayTeam
        holder.rvHomeTeamScores.text = currentItem.homeTeamScores
        holder.rvAwayTeamScores.text = currentItem.awayTeamScores

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return matchesList.size

    }


    class MatchViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val rvHomeTeam: TextView = itemView.findViewById<TextView>(R.id.home_team)
        val rvAwayTeam: TextView = itemView.findViewById<TextView>(R.id.away_team)
        val rvHomeTeamScores: TextView = itemView.findViewById<TextView>(R.id.home_team_scores)
        val rvAwayTeamScores: TextView = itemView.findViewById<TextView>(R.id.away_team_scores)
    }
}