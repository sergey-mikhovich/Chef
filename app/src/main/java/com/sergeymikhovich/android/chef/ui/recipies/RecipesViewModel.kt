package com.sergeymikhovich.android.chef.ui.recipies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergeymikhovich.android.chef.model.Category
import com.sergeymikhovich.android.chef.model.Cuisine
import com.sergeymikhovich.android.chef.model.Recipe
import com.sergeymikhovich.android.chef.repository.ChefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val repository: ChefRepository
) : ViewModel() {

    val recipeDetailsFlow = repository.getRecipeDetailsFlow()

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories = _categories.asStateFlow()

    private val _cuisines = MutableStateFlow<List<Cuisine>>(emptyList())
    val cuisines = _cuisines.asStateFlow()

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

    fun onFavoriteClick(recipe: Recipe) =
        viewModelScope.launch {
            repository.updateRecipe(recipe.copy(isFavorite = !recipe.isFavorite))
        }
}