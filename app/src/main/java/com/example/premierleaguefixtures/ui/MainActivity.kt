package com.example.premierleaguefixtures.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.premierleaguefixtures.data.db.MatchesDatabase
import com.example.premierleaguefixtures.data.repositories.MatchesRepository
import com.example.premierleaguefixtures.databinding.ActivityMainBinding
import com.example.premierleaguefixtures.other.MatchAdapter



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var matchAdapter: MatchAdapter
    lateinit var viewModel: MatchesViewModel
    lateinit var matchesDb: MatchesDatabase
    lateinit var matchesRepository: MatchesRepository
    lateinit var viewModelProviderFactory: MatchesViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Will be removed during the Dependency Injection realization
        matchAdapter = MatchAdapter()
        matchesDb = MatchesDatabase(this)
        matchesRepository = MatchesRepository(matchesDb)
        viewModelProviderFactory = MatchesViewModelProviderFactory(matchesRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[MatchesViewModel::class.java]


    }
}