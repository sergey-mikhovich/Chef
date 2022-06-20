package com.sergeymikhovich.android.chef.ui.recipies.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sergeymikhovich.android.chef.R
import com.sergeymikhovich.android.chef.databinding.ListItemRecipeBinding
import com.sergeymikhovich.android.chef.model.Recipe
import com.sergeymikhovich.android.chef.model.relations.RecipeDetails

class RecipesViewHolder(
    private val binding: ListItemRecipeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(
        data: RecipeDetails,
        showRecipeDetails: (String) -> Unit,
        onFavoriteClick: (Recipe) -> Unit
    ) {
        val context = itemView.context
        val imageResource = context.resources.getIdentifier(data.recipe.uri, null, context.packageName)

        binding.nameCategoryItemRecipe.text = data.category.name
        binding.nameCuisineItemRecipe.text = context.resources.getString(R.string.cuisine_title, data.cuisine.name)
        binding.nameRecipe.text = data.recipe.name
        binding.textCookingTime.text = context.resources.getString(R.string.cooking_time_title, data.recipe.cookingTime)
        binding.textQuantityIngredients.text = context.resources.getString(R.string.quantity_ingredients_title, data.recipe.quantityIngredients)
        Glide.with(itemView)
            .load(if (data.recipe.isFavorite) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
            .into(binding.imageFavoriteButton)
        Glide.with(itemView).load(imageResource).into(binding.imageRecipe)

        binding.cardRecipe.setOnClickListener { showRecipeDetails(data.recipe.id) }
        binding.imageFavoriteButton.setOnClickListener {  onFavoriteClick(data.recipe) }
    }
}