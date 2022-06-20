package com.sergeymikhovich.android.chef.ui.recipies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sergeymikhovich.android.chef.databinding.ListItemRecipeBinding
import com.sergeymikhovich.android.chef.model.Recipe
import com.sergeymikhovich.android.chef.model.relations.RecipeDetails

class RecipesAdapter(
    private val showRecipeDetails: (String) -> Unit,
    private val onFavoriteClick: (Recipe) -> Unit
) : ListAdapter<RecipeDetails, RecipesViewHolder>(RecipesDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val binding = ListItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bindTo(getItem(position), showRecipeDetails, onFavoriteClick)
    }

    private class RecipesDiffCallBack : DiffUtil.ItemCallback<RecipeDetails>() {

        override fun areItemsTheSame(oldItem: RecipeDetails, newItem: RecipeDetails): Boolean =
            oldItem.recipe.id == newItem.recipe.id

        override fun areContentsTheSame(oldItem: RecipeDetails, newItem: RecipeDetails): Boolean =
            oldItem == newItem

        override fun getChangePayload(oldItem: RecipeDetails, newItem: RecipeDetails): Any? {
            return oldItem.recipe.isFavorite != newItem.recipe.isFavorite
        }
    }

}