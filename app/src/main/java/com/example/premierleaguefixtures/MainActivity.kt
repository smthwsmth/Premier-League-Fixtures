package com.example.premierleaguefixtures


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun toSchedule(view: View) {
        val transfer = Intent(this, ScheduleActivity::class.java)
        startActivity(transfer)
        }


    fun toRating(view: View) {
        val transfer = Intent(this, RatingActivity::class.java)
        startActivity(transfer)
    }



}