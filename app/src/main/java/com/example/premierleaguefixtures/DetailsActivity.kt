package com.example.premierleaguefixtures

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.premierleaguefixtures.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        val checkIfNotNull: (String) -> String = {
            if (it == "null") "неизвестно" else it
        }

        val match = intent.getParcelableExtra<MatchItem>("match")
        Log.d(TAG, match.toString())
//
        if (match != null) {
            binding.apply {
                teamsTitles.text = checkIfNotNull(match.homeTeam) + "   " + checkIfNotNull(match.awayTeam)
                homeTeamScores.text = checkIfNotNull(match.homeTeamScore)
                awayTeamScores.text = checkIfNotNull(match.awayTeamScore)
                stadiumName.text = checkIfNotNull(match.location)
                matchNumberInt.text = checkIfNotNull(match.matchNumber)
                roundNumberInt.text = checkIfNotNull(match.roundNumber)
                date.text = checkIfNotNull(match.dateUtc)
                groupName.text = "Группа: " + checkIfNotNull(match.group)
                homeTeamSymbol.setImageResource(match.homeLogo)
                awayTeamSymbol.setImageResource(match.awayLogo)

            }
        }
    }

}