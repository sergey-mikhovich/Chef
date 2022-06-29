package com.sergeymikhovich.android.chef.ui.recipies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergeymikhovich.android.chef.model.*
import com.sergeymikhovich.android.chef.model.responses.recipeSearchResponse.RecipeSearchResponse
import com.sergeymikhovich.android.chef.repository.ChefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val repository: ChefRepository
) : ViewModel() {

    val recipeDetailsFlow by lazy { repository.getRecipeDetailsFlow() }

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories = _categories

    private val _cuisines: MutableLiveData<List<Cuisine>> = MutableLiveData()
    val cuisines = _cuisines

    private val _searchRecipes: MutableLiveData<Resource<RecipeSearchResponse>> = MutableLiveData()
    var searchRecipes = _searchRecipes
    private val searchRecipeNumber = 20

    fun loadCategories() =
        viewModelScope.launch {
            val categories = repository.getCategories()
            _categories.value = categories
        }

    fun loadCuisines() =
        viewModelScope.launch {
            val cuisines = repository.getCuisines()
            _cuisines.value = cuisines
        }

    fun searchNews(searchQuery: String) =
        viewModelScope.launch {
            _searchRecipes.postValue(Resource.Loading())
            val response = repository.searchRecipes(searchQuery, searchRecipeNumber)
            _searchRecipes.postValue(handleSearchNewsResponse(response))
        }

    private fun handleSearchNewsResponse(response: Response<RecipeSearchResponse>): Resource<RecipeSearchResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun onFavoriteClick(recipe: Recipe) =
        viewModelScope.launch {
            repository.updateRecipe(recipe.copy(isFavorite = !recipe.isFavorite))
        }

    fun addRecipe(recipe: Recipe) =
        viewModelScope.launch {
            repository.addRecipe(recipe)
        }

    fun deleteRecipe(recipe: Recipe) =
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
        }

    fun deleteCompositionsByRecipeId(recipeId: String) =
        viewModelScope.launch {
            repository.deleteCompositionsByRecipeId(recipeId)
        }
}