package com.example.premierleaguefixtures


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.premierleaguefixtures.databinding.MatchItemBinding

class MatchAdapter: RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root)

    private var onClickListener: OnClickListener? = null

    private val diffCallback = object : DiffUtil.ItemCallback<MatchItem>() {
        override fun areItemsTheSame(oldItem: MatchItem, newItem: MatchItem): Boolean {
            return oldItem.matchNumber == newItem.matchNumber
        }

        override fun areContentsTheSame(oldItem: MatchItem, newItem: MatchItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var matches: List<MatchItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount() = matches.size

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
            val match = matches[position]
            homeTeam.text = match.homeTeam
            awayTeam.text = match.awayTeam
            homeTeamScores.text = match.homeTeamScore
            awayTeamScores.text = match.awayTeamScore
            holder.itemView.setOnClickListener {
                onClickListener?.onClick(position, match)
            }
        }


    }

    fun setOnClickListener(listener: OnClickListener?) {
        this.onClickListener = listener
    }

    // Interface for the click listener
    interface OnClickListener {
        fun onClick(position: Int, model: MatchItem)
    }
}