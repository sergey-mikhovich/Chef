package com.sergeymikhovich.android.chef.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class CookingStepEntry(
    val id: String = UUID.randomUUID().toString(),
    val stepDescription: String
) : Parcelable
