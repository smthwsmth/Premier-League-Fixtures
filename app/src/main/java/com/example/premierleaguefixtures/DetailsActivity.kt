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

//        val actionBar = supportActionBar
//        actionBar?.setDisplayHomeAsUpEnabled(true)

    }
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