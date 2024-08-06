package com.example.premierleaguefixtures.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.premierleaguefixtures.data.models.MatchItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchesDao {

    @Transaction
    @Query("SELECT * FROM matches_items")
    fun dbGetAllMatchesItems(): Flow<List<MatchItem>>

    @Transaction
    @Query("SELECT * FROM matches_items WHERE (home_team LIKE '%' || :teamName || '%') OR (away_team LIKE '%' || :teamName || '%')")
    fun dbGetMatchByTeamName(teamName: String): Flow<List<MatchItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMatches(matches: List<MatchItem>)

    @Query("DELETE FROM matches_items")
    suspend fun deleteAllMatches()


}