package com.sergeymikhovich.android.chef.model.responses.recipeSearchResponse

data class RecipeSearchResponse(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)