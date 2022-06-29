package com.sergeymikhovich.android.chef.repository

import androidx.lifecycle.LiveData
import com.sergeymikhovich.android.chef.model.*
import com.sergeymikhovich.android.chef.model.relations.*
import com.sergeymikhovich.android.chef.model.responses.recipeSearchResponse.RecipeSearchResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ChefRepository {

    //Cuisine
    suspend fun getCuisines(): List<Cuisine>

    suspend fun addCuisines(cuisines: List<Cuisine>)


    //Category
    suspend fun getCategories(): List<Category>

    suspend fun addCategories(categories: List<Category>)


    //Recipe
    fun getRecipeDetailsFlow(): Flow<List<RecipeDetails>>

    fun getRecipeById(recipeId: String): LiveData<Recipe>

    suspend fun getRecipes(): List<Recipe>

    suspend fun addRecipe(recipe: Recipe)

    suspend fun addRecipes(recipes: List<Recipe>)

    suspend fun updateRecipe(recipe: Recipe)

    suspend fun deleteRecipe(recipe: Recipe)

    suspend fun searchRecipes(query: String, pageNumber: Number): Response<RecipeSearchResponse>


    //Composition
    suspend fun getCompositions(): List<Composition>

    suspend fun addComposition(composition: Composition)

    suspend fun addCompositions(compositions: List<Composition>)

    suspend fun getCompositionsByRecipeId(recipeId: String): List<Composition>

    fun getCompositionsDetailsByRecipeId(recipeId: String): LiveData<List<CompositionDetails>>

    suspend fun deleteComposition(composition: Composition)

    suspend fun deleteCompositionsByRecipeId(recipeId: String)


    //Measurement
    suspend fun getMeasurements(): List<Measurement>

    suspend fun addMeasurements(measurements: List<Measurement>)


    //Ingredient
    suspend fun getIngredients(): List<Ingredient>

    suspend fun addIngredients(ingredients: List<Ingredient>)

}