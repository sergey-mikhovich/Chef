package com.sergeymikhovich.android.chef.ui.recipeSteps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sergeymikhovich.android.chef.databinding.ListItemRecipeStepBinding
import com.sergeymikhovich.android.chef.model.CookingStepEntry

class RecipeStepsAdapter(

) : ListAdapter<CookingStepEntry, RecipeStepsViewHolder>(
    CookingStepEntryDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeStepsViewHolder {
        val binding = ListItemRecipeStepBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return RecipeStepsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeStepsViewHolder, position: Int) {
        holder.bindTo(getItem(position), position + 1)
    }

    private class CookingStepEntryDiffCallback : DiffUtil.ItemCallback<CookingStepEntry>() {

        override fun areItemsTheSame(oldItem: CookingStepEntry, newItem: CookingStepEntry): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CookingStepEntry, newItem: CookingStepEntry): Boolean =
            oldItem == newItem
    }

}