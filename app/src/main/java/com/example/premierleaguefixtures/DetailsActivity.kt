package com.example.premierleaguefixtures

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        appBarSettings()
        bind()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.other_activities, menu)         // inflate the ActionBar with buttons
        return true
    }

    // function to react on ActionBar's buttons pushes
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // checks if button "home" was pressed. If true - go back to previous page
        when(item.itemId) {
            android.R.id.home -> finish()
            R.id.settings -> {
                Toast.makeText(this, "Настройки", Toast.LENGTH_LONG).show()
            }
        }
        return true
    }

    private fun appBarSettings() {
        setSupportActionBar(findViewById(R.id.appbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.title = "Детали матча"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }


    private fun bind() {

        val match = intent.getParcelableExtra<MatchItem>("match")

        if (match != null) {
            val homeTeamName = findViewById<TextView>(R.id.home_team)
            val awayTeamName = findViewById<TextView>(R.id.away_team)
            val homeTeamScores = findViewById<TextView>(R.id.home_team_scores)
            val awayTeamScores = findViewById<TextView>(R.id.away_team_scores)
            val group = findViewById<TextView>(R.id.group_name)
            val location = findViewById<TextView>(R.id.stadium_name)
            val matchNumber = findViewById<TextView>(R.id.match_number_int)
            val roundNumber = findViewById<TextView>(R.id.round_number_int)
            val date =  findViewById<TextView>(R.id.date)

            // I decided to add also icons for each of the team
            val homeTeamSymbol = findViewById<ImageView>(R.id.home_team_symbol)
            val awayTeamSymbol = findViewById<ImageView>(R.id.away_team_symbol)

            homeTeamName.text = match.homeTeam
            awayTeamName.text = match.awayTeam
            homeTeamScores.text = match.homeTeamScores
            awayTeamScores.text = match.awayTeamScores
            location.text = match.location
            matchNumber.text = match.matchNumber
            roundNumber.text = match.roundNumber
            date.text = match.dateTime
            homeTeamSymbol.setImageResource(match.homeLogo)
            awayTeamSymbol.setImageResource(match.awayLogo)

            /* check if Group is known */
            if (match.group == "null") {
                group.text = "неизвестно"
            } else {
                group.text = match.group
            }
        }
    }

}