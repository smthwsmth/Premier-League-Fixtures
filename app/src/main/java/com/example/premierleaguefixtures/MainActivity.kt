package com.example.premierleaguefixtures

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.PopupMenu.OnMenuItemClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appBarSettings()
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

    fun toDetails (view: View) {
        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
    }
}
