package com.sergeymikhovich.android.chef.ui.recipeIngredients

import androidx.recyclerview.widget.RecyclerView
import com.sergeymikhovich.android.chef.databinding.ListItemRecipeIngredientBinding
import com.sergeymikhovich.android.chef.model.relations.CompositionDetails

class RecipeIngredientsViewHolder(
    private val binding: ListItemRecipeIngredientBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(
        data: CompositionDetails
    ) {
        binding.compositionIngredientName.text = data.ingredient.name
        binding.compositionIngredientQuantity.text = data.composition.quantity
        binding.compositionIngredientMeasurement.text = data.measurement.name
    }
}