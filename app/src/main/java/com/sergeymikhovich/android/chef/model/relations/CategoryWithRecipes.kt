package com.sergeymikhovich.android.chef.model.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.sergeymikhovich.android.chef.model.Category
import com.sergeymikhovich.android.chef.model.Recipe
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryWithRecipes(
    @Embedded
    val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val recipes: List<Recipe>
) : Parcelable
