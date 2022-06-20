package com.sergeymikhovich.android.chef.repository

import androidx.lifecycle.LiveData
import com.sergeymikhovich.android.chef.model.*
import com.sergeymikhovich.android.chef.model.relations.*
import kotlinx.coroutines.flow.Flow

interface ChefRepository {

    //Cuisine
    suspend fun getCuisines(): List<Cuisine>

    suspend fun addCuisines(cuisines: List<Cuisine>)


    //Category
    suspend fun getCategories(): List<Category>

    suspend fun addCategories(categories: List<Category>)


    //Recipe
    fun getRecipeDetailsFlow(): Flow<List<RecipeDetails>>

    suspend fun getRecipeById(recipeId: String): Recipe

    suspend fun getRecipes(): List<Recipe>

    suspend fun addRecipes(recipes: List<Recipe>)

    suspend fun updateRecipe(recipe: Recipe)


    //Composition
    suspend fun getCompositions(): List<Composition>

    suspend fun addCompositions(compositions: List<Composition>)

    suspend fun getCompositionsByRecipeId(recipeId: String): List<Composition>

    suspend fun getCompositionsDetailsByRecipeId(recipeId: String): List<CompositionDetails>


    //Measurement
    suspend fun getMeasurements(): List<Measurement>

    suspend fun addMeasurements(measurements: List<Measurement>)


    //Ingredient
    suspend fun getIngredients(): List<Ingredient>

    suspend fun addIngredients(ingredients: List<Ingredient>)

}