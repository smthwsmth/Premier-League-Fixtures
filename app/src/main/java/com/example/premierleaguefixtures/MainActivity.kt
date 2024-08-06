package com.example.premierleaguefixtures

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.premierleaguefixtures.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var matchAdapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appBarSettings()
        setRecyclerView()

        lifecycleScope.launch { flowRetrofitCall() }

        matchAdapter.setOnClickListener(object :
            MatchAdapter.OnClickListener {
            override fun onClick(position: Int, model: MatchItem) {
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)

                // adding the logos for each team
                model.homeLogo = getTeamsLogo(model.homeTeam)
                model.awayLogo = getTeamsLogo(model.awayTeam)
                intent.putExtra("match", model)
                startActivity(intent)
            }
        })
    }

    private suspend fun flowRetrofitCall() {
        val flow = Flow().fetchItems()
        binding.progressBar.isVisible = true
        flow.collect { list -> matchAdapter.matches = list }
        binding.progressBar.isVisible = false
    }


    private fun setRecyclerView() = binding.recyclerview.apply {
        matchAdapter = MatchAdapter()
        adapter = matchAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
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
        setSupportActionBar(binding.appbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
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

        return teamsLogo.getOrDefault(teamName.lowercase(), R.drawable.ic_football)
    }
}