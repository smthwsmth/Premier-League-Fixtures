package com.example.premierleaguefixtures.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.premierleaguefixtures.data.repositories.MatchesRepository


@Suppress("UNCHECKED_CAST")
class MatchesViewModelProviderFactory(
    private val repository: MatchesRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MatchesViewModel(repository) as T
    }
}