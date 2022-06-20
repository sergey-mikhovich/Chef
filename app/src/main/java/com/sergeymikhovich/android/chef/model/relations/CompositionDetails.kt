package com.sergeymikhovich.android.chef.model.relations

import android.icu.util.Measure
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.sergeymikhovich.android.chef.model.Composition
import com.sergeymikhovich.android.chef.model.Ingredient
import com.sergeymikhovich.android.chef.model.Measurement
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompositionDetails(
    @Embedded
    val composition: Composition,

    @Relation(
        parentColumn = "ingredientId",
        entityColumn = "id",
    )
    val ingredient: Ingredient,

    @Relation(
        parentColumn = "measurementId",
        entityColumn = "id",
    )
    val measurement: Measurement
): Parcelable
