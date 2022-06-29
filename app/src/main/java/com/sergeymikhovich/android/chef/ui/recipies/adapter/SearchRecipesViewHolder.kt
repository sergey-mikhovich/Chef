package com.sergeymikhovich.android.chef.ui.recipies.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sergeymikhovich.android.chef.databinding.ListItemRecipeBinding
import com.sergeymikhovich.android.chef.model.responses.recipeSearchResponse.Result

class SearchRecipesViewHolder(
    private val binding: ListItemRecipeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(
        data: Result,
    ) {
        val imageResource = data.image

        binding.nameCategoryItemRecipe.text = "Category"
        binding.nameCuisineItemRecipe.text = "Cuisine"
        binding.nameRecipe.text = data.title
        binding.textCookingTime.text = "Time"
        binding.textQuantityIngredients.text = "quantityIngredients"
        Glide.with(itemView).load(imageResource).into(binding.imageRecipe)
    }
}