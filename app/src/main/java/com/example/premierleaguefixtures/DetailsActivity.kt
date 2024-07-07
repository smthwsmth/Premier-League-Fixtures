package com.example.premierleaguefixtures

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        appBarSettings()

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

    fun appBarSettings() {
        setSupportActionBar(findViewById(R.id.appbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.title = "Детали матча"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }
}