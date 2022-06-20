package com.sergeymikhovich.android.chef.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sergeymikhovich.android.chef.model.Ingredient
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredient")
    suspend fun getIngredients(): List<Ingredient>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIngredients(ingredients: List<Ingredient>)
}