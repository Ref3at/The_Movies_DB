package com.refaat.themoviesdb.domain.model

import android.icu.text.CaseMap
import android.os.Parcel
import android.os.Parcelable
import retrofit2.http.PartMap

data class Movie(
    val id: Int?,
    val title: String?,
    val releaseDate: String?,
    val ratingCount: String?,
    val posterImage: String?,
    val backdropImage: String?,
    var genreIds: List<Int>,
    var genreString: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createIntList(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(releaseDate)
        parcel.writeString(ratingCount)
        parcel.writeString(posterImage)
        parcel.writeString(backdropImage)
        parcel.writeString(genreString)
        parcel.writeIntList(genreIds)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }


}

fun Parcel.createIntList(): List<Int> {
    val size = readInt()
    val output = ArrayList<Int>(size)
    for (i in 0 until size) {
        output.add(readInt())
    }
    return output
}

fun Parcel.writeIntList(input: List<Int>) {
    writeInt(input.size) // Save number of elements.
    input.forEach(this::writeInt) // Save each element.
}