package com.example.premierleaguefixtures.data.repositories


import com.example.premierleaguefixtures.data.db.MatchesDatabase
import com.example.premierleaguefixtures.data.models.MatchItem
import com.example.premierleaguefixtures.data.retrofit.RetrofitInstance

class MatchesRepository(
    private val db: MatchesDatabase
) {

    suspend fun getAllMatchesItems() =
        RetrofitInstance.api.getAllMatchesItems()

    suspend fun insertAllMatches(matchesList: List<MatchItem>) =
        db.getMatchesDao().insertAllMatches(matchesList)

    suspend fun deleteAllMatches() =
        db.getMatchesDao().deleteAllMatches()

    fun getSavedMatches() =
        db.getMatchesDao().dbGetAllMatchesItems()

    fun getMatchByTeamName(teamName: String) =
        db.getMatchesDao().dbGetMatchByTeamName(teamName)


}