package com.sergeymikhovich.android.chef.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
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
    val quantityIngredients: Int = 0,
    @TypeConverters(CookingStepEntryConverter::class)
    val instructions: List<CookingStepEntry>,
    val image: String,
    var isFavorite: Boolean = false
) : Parcelable {

    val uri: String
        get() = "drawable/$image"
}
