package com.sergeymikhovich.android.chef.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sergeymikhovich.android.chef.model.Recipe
import com.sergeymikhovich.android.chef.model.relations.RecipeDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM recipe")
    fun getRecipeDetailsFlow(): Flow<List<RecipeDetails>>

    @Query("SELECT * FROM recipe WHERE id = :recipeId")
    fun getRecipeById(recipeId: String): LiveData<Recipe>

    @Query("SELECT * FROM recipe")
    suspend fun getRecipes(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipes(recipes: List<Recipe>)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)
}