package com.sergeymikhovich.android.chef.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sergeymikhovich.android.chef.database.converters.CookingStepEntryConverter
import com.sergeymikhovich.android.chef.database.dao.*
import com.sergeymikhovich.android.chef.model.*

const val DATABASE_VERSION = 1

@Database(
    entities = [
        Cuisine::class,
        Category::class,
        Recipe::class,
        Composition::class,
        Ingredient::class,
        Measurement::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(
    CookingStepEntryConverter::class
)
abstract class ChefDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun cuisineDao(): CuisineDao

    abstract fun recipeDao(): RecipeDao

    abstract fun compositionItemDao(): CompositionDao

    abstract fun ingredientDao(): IngredientDao

    abstract fun measurementDao(): MeasurementDao
}