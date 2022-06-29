package com.sergeymikhovich.android.chef.ui.recipies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sergeymikhovich.android.chef.databinding.ListItemRecipeBinding
import com.sergeymikhovich.android.chef.model.responses.recipeSearchResponse.Result

class SearchRecipesAdapter : ListAdapter<Result, SearchRecipesViewHolder>(SearchRecipesDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecipesViewHolder {
        val binding = ListItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchRecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchRecipesViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    private class SearchRecipesDiffCallBack : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem == newItem
    }
}