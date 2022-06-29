package com.sergeymikhovich.android.chef.ui.filter

sealed class Filter {

    class ByCategory(val categoryId: String) : Filter()

    class ByCuisine(val cuisineId: String) : Filter()
}

