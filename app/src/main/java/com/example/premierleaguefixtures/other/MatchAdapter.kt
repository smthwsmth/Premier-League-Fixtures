package com.example.premierleaguefixtures.other


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.premierleaguefixtures.data.models.MatchItem
import com.example.premierleaguefixtures.databinding.MatchItemBinding

class MatchAdapter: RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root)



    private val diffCallback = object : DiffUtil.ItemCallback<MatchItem>() {
        override fun areItemsTheSame(oldItem: MatchItem, newItem: MatchItem): Boolean {
            return oldItem.matchNumber == newItem.matchNumber
        }

        override fun areContentsTheSame(oldItem: MatchItem, newItem: MatchItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun getItemCount() = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            MatchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.binding.apply {
            val match = differ.currentList[position]
            homeTeam.text = match.homeTeam
            awayTeam.text = match.awayTeam
            homeTeamScores.text = match.homeTeamScore
            awayTeamScores.text = match.awayTeamScore
            holder.itemView.setOnClickListener {
                onClickListener?.onClick(position, match)
            }
        }

    }

    private var onClickListener: OnClickListener? = null
    fun setOnClickListener(listener: OnClickListener?) {
        this.onClickListener = listener
    }

    // Interface for the click listener
    interface OnClickListener {
        fun onClick(position: Int, model: MatchItem)
    }
}