package com.example.premierleaguefixtures

import android.os.Parcel
import android.os.Parcelable

data class MatchItem(
    val dateTime: String,
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamScores: String,
    val awayTeamScores: String,
    val matchNumber: String,
    val roundNumber: String,
    val location: String,
    val group: String,
    var homeLogo: Int = R.drawable.ic_football,
    var awayLogo: Int = R.drawable.ic_football
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dateTime)
        parcel.writeString(homeTeam)
        parcel.writeString(awayTeam)
        parcel.writeString(homeTeamScores)
        parcel.writeString(awayTeamScores)
        parcel.writeString(matchNumber)
        parcel.writeString(roundNumber)
        parcel.writeString(location)
        parcel.writeString(group)
        parcel.writeInt(homeLogo)
        parcel.writeInt(awayLogo)


    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MatchItem> {
        override fun createFromParcel(parcel: Parcel): MatchItem {
            return MatchItem(parcel)
        }

        override fun newArray(size: Int): Array<MatchItem?> {
            return arrayOfNulls(size)
        }
    }
}
