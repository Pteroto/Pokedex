package com.doubleg.pokedex.repository.model

import android.os.Parcel
import android.os.Parcelable


class Sprites(val front_default: String?, val front_shiny: String?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString())

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(front_default)
        dest?.writeString(front_shiny)
    }

    companion object CREATOR : Parcelable.Creator<Sprites> {
        override fun createFromParcel(parcel: Parcel): Sprites {
            return Sprites(parcel)
        }

        override fun newArray(size: Int): Array<Sprites?> {
            return arrayOfNulls(size)
        }
    }
}