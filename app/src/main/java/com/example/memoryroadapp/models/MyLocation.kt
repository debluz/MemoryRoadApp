package com.example.memoryroadapp.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.Exclude

data class MyLocation(
    var userId: String? = null,
    var name: String? = null,
    var longitude: Float? = null,
    var latitude: Float? = null,
    var diameter: Double? = null,
    var description: String? = null,
    var uid: String? = null,
    var imageName: String? = null,
    var imageUrl: String? = null,
    @get:Exclude var isCreated: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(name)
        parcel.writeValue(longitude)
        parcel.writeValue(latitude)
        parcel.writeValue(diameter)
        parcel.writeString(description)
        parcel.writeString(uid)
        parcel.writeString(imageName)
        parcel.writeString(imageUrl)
        parcel.writeByte(if (isCreated) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyLocation> {
        override fun createFromParcel(parcel: Parcel): MyLocation {
            return MyLocation(parcel)
        }

        override fun newArray(size: Int): Array<MyLocation?> {
            return arrayOfNulls(size)
        }
    }
}