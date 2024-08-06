package com.example.premierleaguefixtures.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.premierleaguefixtures.data.models.MatchItem

@Database(
    entities = [MatchItem::class],
    version = 1
)
abstract class MatchesDatabase: RoomDatabase() {

    abstract fun getMatchesDao(): MatchesDao

    companion object {

        @Volatile
        private var instance: MatchesDatabase? = null


        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MatchesDatabase::class.java, "MatchesDB.db").build()
    }


}