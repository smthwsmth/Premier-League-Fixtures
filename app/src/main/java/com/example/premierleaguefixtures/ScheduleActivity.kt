package com.example.premierleaguefixtures

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)


        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
    // function to display all buttons on ActionBar from "menu---> main.xml"
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)         // inflate the ActionBar with buttons
        return true
    }

    // function to react on ActionBar's buttons pushes
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // checks if button "home" was pressed. If true - go back to previous page
        when(item.itemId) {
            android.R.id.home -> finish()
            R.id.dropdown_menu -> {
                Toast.makeText(this, "Dropdown Menu", Toast.LENGTH_LONG).show()
            }
        }
        return true

    }
}