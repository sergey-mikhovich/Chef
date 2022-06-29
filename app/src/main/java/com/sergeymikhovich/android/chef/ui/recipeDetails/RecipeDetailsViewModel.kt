package com.sergeymikhovich.android.chef.ui.recipeDetails

import androidx.lifecycle.*
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

    private val _recipeId = MutableLiveData("")

    var recipe: LiveData<Recipe> =
        Transformations.switchMap(_recipeId) { recipeId ->
            repository.getRecipeById(recipeId)
        }

    var recipeDetails: LiveData<List<CompositionDetails>> =
        Transformations.switchMap(_recipeId) { recipeId ->
            repository.getCompositionsDetailsByRecipeId(recipeId)
        }

    fun loadRecipeId(recipeId: String) {
        _recipeId.value = recipeId
    }
}