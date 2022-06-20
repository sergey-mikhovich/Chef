package com.sergeymikhovich.android.chef.model.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.sergeymikhovich.android.chef.model.Cuisine
import com.sergeymikhovich.android.chef.model.Recipe
import kotlinx.parcelize.Parcelize

@Parcelize
data class CuisineWithRecipes(
    @Embedded
    val cuisine: Cuisine,
    @Relation(
        parentColumn = "id",
        entityColumn = "cuisineId"
    )
    val recipes: List<Recipe>
) : Parcelable