package com.sergeymikhovich.android.chef.repository

import androidx.lifecycle.LiveData
import com.sergeymikhovich.android.chef.database.dao.*
import com.sergeymikhovich.android.chef.model.*
import com.sergeymikhovich.android.chef.model.relations.*
import com.sergeymikhovich.android.chef.networking.RecipesApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChefRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val cuisineDao: CuisineDao,
    private val recipeDao: RecipeDao,
    private val compositionDao: CompositionDao,
    private val measurementDao: MeasurementDao,
    private val ingredientDao: IngredientDao,
    private val recipesApiService: RecipesApiService
) : ChefRepository {

    //Cuisine
    override suspend fun getCuisines(): List<Cuisine> = cuisineDao.getCuisines()

    override suspend fun addCuisines(cuisines: List<Cuisine>) = cuisineDao.addCuisines(cuisines)


    //Category
    override suspend fun getCategories(): List<Category> = categoryDao.getCategories()

    override suspend fun addCategories(categories: List<Category>) = categoryDao.addCategories(categories)


    //Recipe
    override fun getRecipeDetailsFlow(): Flow<List<RecipeDetails>> {
        return recipeDao.getRecipeDetailsFlow().map { recipeDetails ->
            recipeDetails.map { recipeDetail ->
                val compositions = compositionDao.getCompositionsByRecipeId(recipeDetail.recipe.id)
                val quantity = compositions.size

                RecipeDetails(
                    recipeDetail.recipe.copy(quantityIngredients = quantity),
                    recipeDetail.cuisine,
                    recipeDetail.category
                )
            }
        }
    }

    override fun getRecipeById(recipeId: String): LiveData<Recipe> = recipeDao.getRecipeById(recipeId)

    override suspend fun getRecipes(): List<Recipe> = recipeDao.getRecipes()

    override suspend fun addRecipe(recipe: Recipe) = recipeDao.addRecipe(recipe)

    override suspend fun addRecipes(recipes: List<Recipe>) = recipeDao.addRecipes(recipes)

    override suspend fun updateRecipe(recipe: Recipe) = recipeDao.updateRecipe(recipe)

    override suspend fun deleteRecipe(recipe: Recipe) = recipeDao.deleteRecipe(recipe)

    override suspend fun searchRecipes(query: String, pageNumber: Number) =
        recipesApiService.searchForRecipes(query, pageNumber)


    //Composition
    override suspend fun getCompositions(): List<Composition> = compositionDao.getCompositions()

    override suspend fun addComposition(composition: Composition) = compositionDao.addComposition(composition)

    override suspend fun addCompositions(compositions: List<Composition>) = compositionDao.addCompositions(compositions)

    override suspend fun getCompositionsByRecipeId(recipeId: String): List<Composition> =
        compositionDao.getCompositionsByRecipeId(recipeId)

    override fun getCompositionsDetailsByRecipeId(recipeId: String): LiveData<List<CompositionDetails>> =
        compositionDao.getCompositionsDetailsByRecipeId(recipeId)

    override suspend fun deleteComposition(composition: Composition) = compositionDao.deleteComposition(composition)

    override suspend fun deleteCompositionsByRecipeId(recipeId: String) {
        val compositions = compositionDao.getCompositionsByRecipeId(recipeId)

        for (composition in compositions) {
            compositionDao.deleteComposition(composition)
        }
    }


    //Measurement
    override suspend fun getMeasurements(): List<Measurement> = measurementDao.getMeasurements()

    override suspend fun addMeasurements(measurements: List<Measurement>) = measurementDao.addMeasurements(measurements)


    //Ingredient
    override suspend fun getIngredients(): List<Ingredient> = ingredientDao.getIngredients()

    override suspend fun addIngredients(ingredients: List<Ingredient>) = ingredientDao.addIngredients(ingredients)
}