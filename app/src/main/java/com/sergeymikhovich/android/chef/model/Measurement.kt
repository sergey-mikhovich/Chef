package com.sergeymikhovich.android.chef.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity
data class Measurement(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val abbreviation: String
) : Parcelable
