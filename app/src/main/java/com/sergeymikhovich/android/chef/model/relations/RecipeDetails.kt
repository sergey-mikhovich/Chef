package com.sergeymikhovich.android.chef.model.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.sergeymikhovich.android.chef.model.Category
import com.sergeymikhovich.android.chef.model.Composition
import com.sergeymikhovich.android.chef.model.Cuisine
import com.sergeymikhovich.android.chef.model.Recipe
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeDetails(
    @Embedded
    val recipe: Recipe,

    @Relation(
        parentColumn = "cuisineId",
        entityColumn = "id"
    )
    val cuisine: Cuisine,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: Category,
) : Parcelable