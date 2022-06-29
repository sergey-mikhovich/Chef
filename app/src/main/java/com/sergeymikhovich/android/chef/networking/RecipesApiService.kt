package com.sergeymikhovich.android.chef.networking

import com.sergeymikhovich.android.chef.model.responses.recipeSearchResponse.RecipeSearchResponse
import com.sergeymikhovich.android.chef.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApiService {

    @GET("/recipes/complexSearch")
    suspend fun searchForRecipes(
        @Query("query")
        query: String,
        @Query("number")
        number: Number,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<RecipeSearchResponse>
}