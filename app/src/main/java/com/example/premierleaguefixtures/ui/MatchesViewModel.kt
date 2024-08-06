package com.example.premierleaguefixtures.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.premierleaguefixtures.data.models.MatchItem
import com.example.premierleaguefixtures.data.models.MatchesArrayList
import com.example.premierleaguefixtures.data.repositories.MatchesRepository
import kotlinx.coroutines.launch
import retrofit2.Response


class MatchesViewModel(
    private val matchesRepository: MatchesRepository
): ViewModel() {

    val allMatches: MutableLiveData<List<MatchItem>> = MutableLiveData()

    val searchMatches: MutableLiveData<List<MatchItem>> = MutableLiveData()



    init {
        getAllMatchesItems()
    }


    fun getAllMatchesItems() = viewModelScope.launch {
        allMatches.postValue(listOf())
        val response = matchesRepository.getAllMatchesItems() //  Response retrofit
            // сохраняем данные в БД
        handleResponse(response)
        Log.d("DB", allMatches.value.toString())
        getSavedMatches().collect { allMatches.postValue(it) }

        }


    private fun handleResponse(response: Response<MatchesArrayList>) {
        if (response.isSuccessful) {

            response.body()?.let { resultResponse ->
                saveAllMatches(resultResponse)
            }
        } else {
        throw Exception("Response is not successful") }

    }

    fun searchMatch(searchQuery: String) = viewModelScope.launch {
        getMatchesByTeamName(searchQuery).collect { searchMatches.postValue(it) }
    }


    fun saveAllMatches(matches: List<MatchItem>) = viewModelScope.launch {
        matchesRepository.insertAllMatches(matches)
    }


    fun deleteAllMatches() = viewModelScope.launch {
        matchesRepository.deleteAllMatches()
    }


    fun getSavedMatches() = matchesRepository.getSavedMatches()

    fun getMatchesByTeamName(teamName: String) = matchesRepository.getMatchByTeamName(teamName)
}