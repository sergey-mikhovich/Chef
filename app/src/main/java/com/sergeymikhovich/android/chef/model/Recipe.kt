package com.sergeymikhovich.android.chef.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sergeymikhovich.android.chef.database.converters.CookingStepEntryConverter
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity
data class Recipe(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val cuisineId: String,
    val categoryId: String,
    val cookingTime: Int,
    val quantityIngredients: Int,
    @TypeConverters(CookingStepEntryConverter::class)
    val instructions: List<CookingStepEntry>,
    val image: String,
    val isFavorite: Boolean
) : Parcelable {

    val uri: String
        get() = "drawable/$image"
}
