package com.example.premierleaguefixtures.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.navArgs
import com.example.premierleaguefixtures.R
import com.example.premierleaguefixtures.data.models.MatchItem
import com.example.premierleaguefixtures.ui.MainActivity
import com.example.premierleaguefixtures.ui.MatchesViewModel

class DetailsFragment : Fragment(R.layout.fragment_details), MenuProvider {
    lateinit var viewModel: MatchesViewModel

    val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().addMenuProvider(this, viewLifecycleOwner)
        activity?.setTitle("Match Details")

        viewModel = (activity as MainActivity).viewModel


        val matchItem = args.match
        bind(matchItem)



    }

    private fun bind(matchItem: MatchItem) {

        val checkIfNotNull: (String) -> String = {
            if (it == "null") "неизвестно" else it
        }
            view?.apply {
                findViewById<TextView>(R.id.teams_titles).text = checkIfNotNull(matchItem.homeTeam) + "   " + checkIfNotNull(matchItem.awayTeam)
                findViewById<TextView>(R.id.home_team_scores).text = checkIfNotNull(matchItem.homeTeamScore)
                findViewById<TextView>(R.id.away_team_scores).text = checkIfNotNull(matchItem.awayTeamScore)
                findViewById<TextView>(R.id.stadium_name).text = checkIfNotNull(matchItem.location)
                findViewById<TextView>(R.id.match_number_int).text = checkIfNotNull(matchItem.matchNumber.toString())
                findViewById<TextView>(R.id.round_number_int).text = checkIfNotNull(matchItem.roundNumber)
                findViewById<TextView>(R.id.date).text = checkIfNotNull(matchItem.dateUtc)
                findViewById<TextView>(R.id.group_name).text = "Группа: ${if (matchItem.group == null) "неизвестно" else {matchItem.group}}"
                findViewById<ImageView>(R.id.home_team_symbol).setImageResource(getTeamsLogo(matchItem.homeTeam))
                findViewById<ImageView>(R.id.away_team_symbol).setImageResource(getTeamsLogo(matchItem.awayTeam))


        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.details_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }

    fun getTeamsLogo(teamName: String): Int {
        val teamsLogo = mapOf(
            "arsenal" to R.drawable.arsenal,
            "aston villa" to R.drawable.aston_villa,
            "brentford" to R.drawable.brentford,
            "brighton" to R.drawable.brighton,
            "burnley" to R.drawable.burnley,
            "chelsea" to R.drawable.chelsea,
            "crystal palace" to R.drawable.crystal_palace,
            "everton" to R.drawable.everton,
            "leeds" to R.drawable.leeds,
            "leicester" to R.drawable.leicester,
            "liverpool" to R.drawable.liverpool,
            "man city" to R.drawable.mancity,
            "man utd" to R.drawable.manutd,
            "newcastle" to R.drawable.newcastle,
            "norwich" to R.drawable.norwich,
            "southampton" to R.drawable.southampton,
            "spurs" to R.drawable.spurs,
            "watford" to R.drawable.watford,
            "west ham" to R.drawable.west_ham,
            "wolves" to R.drawable.wolves
        )

        return teamsLogo.getOrDefault(teamName.lowercase(), R.drawable.ic_football)
    }
}