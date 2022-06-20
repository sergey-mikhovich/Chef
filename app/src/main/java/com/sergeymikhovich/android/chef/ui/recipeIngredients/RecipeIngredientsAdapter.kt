package com.sergeymikhovich.android.chef.ui.recipeIngredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sergeymikhovich.android.chef.databinding.ListItemRecipeIngredientBinding
import com.sergeymikhovich.android.chef.model.relations.CompositionDetails

class RecipeIngredientsAdapter
    : ListAdapter<CompositionDetails, RecipeIngredientsViewHolder>(
    CompositionItemDiffCallback()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeIngredientsViewHolder {
        val binding = ListItemRecipeIngredientBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return RecipeIngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeIngredientsViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    private class CompositionItemDiffCallback : DiffUtil.ItemCallback<CompositionDetails>() {
        override fun areItemsTheSame(oldItem: CompositionDetails, newItem: CompositionDetails): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CompositionDetails, newItem: CompositionDetails): Boolean =
            oldItem == newItem
    }
}