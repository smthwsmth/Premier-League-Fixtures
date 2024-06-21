package com.example.premierleaguefixtures

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

    }

    fun toSchedule(view: View) {
        val transfer = Intent(this, ScheduleActivity::class.java)
        startActivity(transfer)
        }


    fun toResults(view: View) {
        val transfer = Intent(this, ResultsActivity::class.java)
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

    fun goBack(view: View) {
        val transfer = Intent(this, MainActivity::class.java)
        startActivity(transfer)
    }
}