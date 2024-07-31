package com.example.premierleaguefixtures.data.models


import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "matches_items")
data class MatchItem(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "match_number")
    @SerializedName("MatchNumber")
    val matchNumber: String,

    @ColumnInfo(name = "round_number")
    @SerializedName("RoundNumber")
    val roundNumber: String,

    @ColumnInfo(name = "date_utc")
    @SerializedName("DateUtc")
    val dateUtc: String,

    @ColumnInfo(name = "location")
    @SerializedName("Location")
    val location: String,

    @ColumnInfo(name = "home_team")
    @SerializedName("HomeTeam")
    val homeTeam: String,

    @ColumnInfo(name = "away_team")
    @SerializedName("AwayTeam")
    val awayTeam: String,

    @ColumnInfo(name = "group")
    @SerializedName("Group")
    val group: String?,

    @ColumnInfo(name = "home_team_score")
    @SerializedName("HomeTeamScore")
    val homeTeamScore: String,

    @ColumnInfo(name = "away_team_score")
    @SerializedName("AwayTeamScore")
    val awayTeamScore: String,

) : Serializable