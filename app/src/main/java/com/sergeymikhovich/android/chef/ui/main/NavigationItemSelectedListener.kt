package com.sergeymikhovich.android.chef.ui.main

import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationBarView
import com.sergeymikhovich.android.chef.R
import com.sergeymikhovich.android.chef.ui.recipies.FavoriteRecipesFragment
import com.sergeymikhovich.android.chef.ui.recipies.RecipesFragment
import com.sergeymikhovich.android.chef.ui.recipies.SearchRecipesFragment

class NavigationItemSelectedListener(
    private val fragmentManager: FragmentManager
) : NavigationBarView.OnItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.allRecipes -> {
                fragmentManager.commit {
                    replace<RecipesFragment>(R.id.fragment_container_main)
                    setReorderingAllowed(true)
                }
                true
            }
            R.id.searchRecipes -> {
                fragmentManager.commit {
                    replace<SearchRecipesFragment>(R.id.fragment_container_main)
                    setReorderingAllowed(true)
                }
                true
            }
            R.id.favoriteRecipes -> {
                fragmentManager.commit {
                    replace<FavoriteRecipesFragment>(R.id.fragment_container_main)
                    setReorderingAllowed(true)
                }
                true
            }
            else -> false
        }
    }
}