package com.example.premierleaguefixtures

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    lateinit var matchesList: ArrayList<MatchItem>
    private lateinit var matchAdapter: MatchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appBarSettings()

        matchesList = arrayListOf()

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        readJSONFromAssets("matches.json")
        matchAdapter = MatchAdapter(matchesList)
        recyclerView.adapter = matchAdapter

        /* processing of item clicks for getting more information about a match */
        matchAdapter.onItemClick = {
            val intent = Intent(this, DetailsActivity::class.java)

            /* getting a team logo by means of the getTeamsLogo function */
            val homeTeamLogo = getTeamsLogo(it.homeTeam.lowercase())
            val awayTeamLogo = getTeamsLogo(it.awayTeam.lowercase())

            // assigning team's logos to properties of MatchItem dataclass
            it.homeLogo = homeTeamLogo
            it.awayLogo = awayTeamLogo
            intent.putExtra("match", it)
            startActivity(intent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)         // inflate the ActionBar with buttons
        return true
    }

    // function to react on ActionBar's buttons pushes
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // checks if button "home" was pressed. If true - go back to previous page
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.search -> {
                Toast.makeText(this, "Поисковая строка", Toast.LENGTH_SHORT).show()
            }

            R.id.settings -> {
                Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
            }
        }
        return true

    }

    private fun appBarSettings() {
        setSupportActionBar(findViewById(R.id.appbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
    }



    /* parsing a json file with matches info */
    fun readJSONFromAssets(path: String) {

        try {
            val inputStream: InputStream = assets.open("matches.json")
            val json = inputStream.bufferedReader().use(BufferedReader::readText)
            var jsonarr = JSONArray(json)
            for (i in 0..< jsonarr.length()) {
                val jsonobj = jsonarr.getJSONObject(i)
                val homeTeamName = jsonobj.getString("HomeTeam")
                val awayTeamName = jsonobj.getString("AwayTeam")
                val matchNumber = jsonobj.getString("MatchNumber")
                val roundNumber = jsonobj.getString("RoundNumber")
                val dateUtc = jsonobj.getString("DateUtc")
                val location = jsonobj.getString("Location")
                val group: String = jsonobj.getString("Group")
                val homeTeamScore = jsonobj.getString("HomeTeamScore")
                val awayTeamScore = jsonobj.getString("AwayTeamScore")

                val match = MatchItem(
                    dateUtc,
                    homeTeamName,
                    awayTeamName,
                    homeTeamScore,
                    awayTeamScore,
                    matchNumber,
                    roundNumber,
                    location,
                    group
                )
                matchesList.add(match)
            }

        } catch (e: IOException) {

        }
    }



    /* creating a function that returns icon in accordance with a key (team name) */
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

        return teamsLogo.getOrDefault(teamName, R.drawable.ic_football)
    }
}