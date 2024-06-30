package com.example.premierleaguefixtures


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        // allow usage of ActionBar
        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)   // <-- button "arrow back"
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
                Toast.makeText(this, "Dropdown Menu", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    fun toSchedule(view: View) {
        val transfer = Intent(this, ScheduleActivity::class.java)
        startActivity(transfer)
        }


    fun toInfo(view: View) {
        val transfer = Intent(this, InfoActivity::class.java)
        startActivity(transfer)
    }


    fun toRatings(view: View) {
        val transfer = Intent(this, RatingActivity::class.java)
        startActivity(transfer)
    }

    fun toNews(view: View) {
        val transfer = Intent(this, NewsActivity::class.java)
        startActivity(transfer)
    }


}