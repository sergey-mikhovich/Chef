package com.sergeymikhovich.android.chef.ui.recipeSteps

import androidx.recyclerview.widget.RecyclerView
import com.sergeymikhovich.android.chef.databinding.ListItemRecipeStepBinding
import com.sergeymikhovich.android.chef.model.CookingStepEntry

class RecipeStepsViewHolder(
    private val binding: ListItemRecipeStepBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(
        data: CookingStepEntry,
        position: Int
    ) {
        binding.stepNumber.text = position.toString()
        binding.stepInstruction.text = data.stepDescription
    }
}