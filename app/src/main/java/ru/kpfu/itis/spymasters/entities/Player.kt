package ru.kpfu.itis.spymasters.entities

import android.os.Parcel
import android.os.Parcelable

data class Player(
    var name: String?,
    var isSpy: Boolean,
    var id: Int? = null,
    var votingId: Int? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeByte(if (isSpy) 1 else 0)
        parcel.writeInt(id ?: -1)
        parcel.writeInt(votingId ?: -1)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}
