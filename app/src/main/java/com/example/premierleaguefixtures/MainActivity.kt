package com.example.premierleaguefixtures

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    }
    fun goToMenu(view: View) {
        val transfer = Intent(this, SecondActivity::class.java)
        startActivity(transfer)
    }
}


