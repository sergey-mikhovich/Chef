package com.sergeymikhovich.android.chef.ui.recipeDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergeymikhovich.android.chef.model.Recipe
import com.sergeymikhovich.android.chef.model.relations.CompositionDetails
import com.sergeymikhovich.android.chef.repository.ChefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val repository: ChefRepository
) : ViewModel() {

    private val _recipeIdFlow = MutableStateFlow("")

    var recipeFlow: StateFlow<Recipe> = _recipeIdFlow.map { recipeId ->
        repository.getRecipeById(recipeId)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Recipe(name = "", cuisineId = "", categoryId = "", cookingTime = 0, quantityIngredients = 0, instructions = emptyList(), image = "")
    )

    var recipeDetailsFlow: StateFlow<List<CompositionDetails>> = _recipeIdFlow.map { recipeId ->
        repository.getCompositionsDetailsByRecipeId(recipeId)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun loadRecipeId(recipeId: String) {
        _recipeIdFlow.value = recipeId
    }
}