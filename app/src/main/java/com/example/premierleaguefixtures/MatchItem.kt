package com.example.premierleaguefixtures


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MatchItem(
    @SerializedName("MatchNumber")
    val matchNumber: String,
    @SerializedName("RoundNumber")
    val roundNumber: String,
    @SerializedName("DateUtc")
    val dateUtc: String,
    @SerializedName("Location")
    val location: String,
    @SerializedName("HomeTeam")
    val homeTeam: String,
    @SerializedName("AwayTeam")
    val awayTeam: String,
    @SerializedName("Group")
    val group: String,
    @SerializedName("HomeTeamScore")
    val homeTeamScore: String,
    @SerializedName("AwayTeamScore")
    val awayTeamScore: String,

    var homeLogo: Int = R.drawable.ic_football,
    var awayLogo: Int = R.drawable.ic_football
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(matchNumber)
        parcel.writeString(roundNumber)
        parcel.writeString(dateUtc)
        parcel.writeString(location)
        parcel.writeString(homeTeam)
        parcel.writeString(awayTeam)
        parcel.writeString(group)
        parcel.writeString(homeTeamScore)
        parcel.writeString(awayTeamScore)
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