package com.sergeymikhovich.android.chef

import android.app.Application
import com.sergeymikhovich.android.chef.model.*
import com.sergeymikhovich.android.chef.repository.ChefRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var repository: ChefRepository

    override fun onCreate() {
        super.onCreate()
        prePopulateDatabase()
    }

    private fun prePopulateDatabase() {
        GlobalScope.launch {
            populateCategories()
            populateCuisines()
            populateRecipes()
            populateCompositions()
            populateIngredients()
            populateMeasurements()
        }
    }

    private suspend fun populateCategories() {
        val categories = repository.getCategories()

            if (categories.isEmpty()) {
                repository.addCategories(listOf(
                    Category(id = "1", name = "Breakfast", image = "breakfast"),
                    Category(id = "2", name = "Lunch", image = "lunch"),
                    Category(id = "3", name = "Dinner", image = "dinner"),
                    Category(id = "4", name = "Appetizer", image = "appetizer"),
                    Category(id = "5", name = "Salad", image = "salad"),
                    Category(id = "6", name = "Main-course", image = "main_course"),
                    Category(id = "7", name = "Side-dish", image = "side_dish"),
                    Category(id = "8", name = "Baked-good", image = "baked_good"),
                    Category(id = "9", name = "Dessert", image = "dessert"),
                    Category(id = "10", name = "Snack", image = "snack"),
                    Category(id = "11", name = "Soup", image = "soup"),
                    Category(id = "12", name = "Sauce", image = "sauce")
                ))
            }
    }

    private suspend fun populateCuisines() {
        val cuisines = repository.getCuisines()

        if (cuisines.isEmpty()) {
            repository.addCuisines(listOf(
                Cuisine(id = "1", name = "Belarusian", image = "belarusian"),
                Cuisine(id = "2", name = "Russian", image = "russian"),
                Cuisine(id = "3", name = "Italian", image = "italian"),
                Cuisine(id = "4", name = "American", image = "american"),
                Cuisine(id = "5", name = "Mexican", image = "mexican"),
                Cuisine(id = "6", name = "Vietnamese", image = "vietnamese"),
                Cuisine(id = "7", name = "African", image = "african"),
                Cuisine(id = "8", name = "French", image = "french"),
                Cuisine(id = "9", name = "Japanese", image = "japanese"),
                Cuisine(id = "10", name = "Indian", image = "indian")
            ))
        }
    }

    private suspend fun populateRecipes() {
        val recipes = repository.getRecipes()

        if (recipes.isEmpty()) {
            repository.addRecipes(listOf(
                Recipe(
                    id = "1",
                    name = "Meat Stuffed Potato Pancakes (Draniki)",
                    cuisineId = "1",
                    categoryId = "4",
                    cookingTime = 45,
                    image = "draniki",
                    instructions = listOf(
                        CookingStepEntry(id = "1", "Into a large bowl, grate potatoes on the star grater*. It " +
                                "should be the consistency of applesauce. Use a spoon to skim off any excess potato liquid " +
                                "that floats to the top."),
                        CookingStepEntry(id = "2", "Grate onion into the same bowl (reserving 1 Tbsp grated onion " +
                                "for meat mixture). Onion keeps potatoes from browning."),
                        CookingStepEntry(id = "3", "Add 1 egg, 3 Tbsp flour, 1 Tbsp sour cream, 1 tsp salt and 1/8 " +
                                "tsp black pepper and stir well."),
                        CookingStepEntry(id = "4", "Mix together ground pork, 1 Tbsp reserved grated onion, 1/4 tsp " +
                                "salt and black pepper to taste. Form into 16 skinny patties and place them on a clean surface."),
                        CookingStepEntry(id = "5", "Heat large non-stick skillet over medium heat and add 2-3 Tbsp oil. " +
                                "Once oil is hot, add 1 Tbsp of potato mixture at a time into the skillet, flattening it out. " +
                                "Top with a thin meat patty and cover the meat with another Tablespoon of potato batter. Saute " +
                                "until potatoes are golden brown then flip and continue sautéing until golden brown and cooked " +
                                "through (about 4 min per side). Repeat with remaining batter**, adding more oil as needed. " +
                                "Remove to paper-towel lined plate and serve with sour cream.")
                    )),
                Recipe(
                    id = "2",
                    name = "Borscht",
                    cuisineId = "2",
                    categoryId = "11",
                    cookingTime = 130,
                    image = "borscht",
                    instructions = listOf()),
                Recipe(
                    id = "3",
                    name = "Slow Cooker Bolognese Sauce",
                    cuisineId = "3",
                    categoryId = "12",
                    cookingTime = 50,
                    image = "bolognese",
                    instructions = listOf(
                        CookingStepEntry(id = "1", "To begin, prepare the following ingredients. Finely dice the " +
                                "onion; wash, peel, and finely dice the carrot. If you have a food chopper or processor you can" +
                                "use this."),
                        CookingStepEntry(id = "2", "Add the oil to a plan along with the ingredients you have just" +
                                "prepared and cook for 10 minutes, stirring occasionally."),
                        CookingStepEntry(id = "3", "After these 10 minutes, raise the heat and add the ground meat. " +
                                "Make sure you break it up well in the pot using a spoon. Add salt and pepper to your liking. " +
                                "Mix and when you see that the meat is no longer red, or that it has begun to cook, lower the heat."),
                        CookingStepEntry(id = "4", "Add the tomato, and add more salt and pepper. Cook over low heat for " +
                                "30-40 more minutes until the sauce thickens."))),
                Recipe(
                    id = "4",
                    name = "Juicy Barbecued Chicken Thighs",
                    cuisineId = "4",
                    categoryId = "6",
                    cookingTime = 50,
                    image = "bbq_chicken",
                    instructions = listOf()),
                Recipe(
                    id = "5",
                    name = "Tostada",
                    cuisineId = "5",
                    categoryId = "6",
                    cookingTime = 30,
                    image = "tostada",
                    instructions = listOf()),
                Recipe(
                    id = "6",
                    name = "Chocolate Souffle",
                    cuisineId = "8",
                    categoryId = "9",
                    cookingTime = 45,
                    image = "chocolate_souffle",
                    instructions = listOf()),
            ))
        }
    }

    private suspend fun populateCompositions() {
        val compositions = repository.getCompositions()

        if (compositions.isEmpty()) {
            repository.addCompositions(listOf(
                //Slow Cooker Bolognese Sauce
                Composition(id = "1", recipeId = "3", ingredientId = "1", quantity = "1", measurementId = "7"),
                Composition(id = "2", recipeId = "3", ingredientId = "2", quantity = "9", measurementId = "9"),
                Composition(id = "3", recipeId = "3", ingredientId = "3", quantity = "2", measurementId = "9"),
                Composition(id = "4", recipeId = "3", ingredientId = "4", quantity = "1/4", measurementId = "8"),
                Composition(id = "5", recipeId = "3", ingredientId = "5", quantity = "1/2", measurementId = "12"),
                Composition(id = "6", recipeId = "3", ingredientId = "6", quantity = "2", measurementId = "4"),
                Composition(id = "7", recipeId = "3", ingredientId = "7", quantity = "9", measurementId = "9"),

                //Meat Stuffed Potato Pancakes (Draniki)
                Composition(id = "8", recipeId = "1", ingredientId = "8", quantity = "5", measurementId = "7"),
                Composition(id = "9", recipeId = "1", ingredientId = "3", quantity = "1", measurementId = "6"),
                Composition(id = "10", recipeId = "1", ingredientId = "9", quantity = "1", measurementId = "6"),
                Composition(id = "11", recipeId = "1", ingredientId = "10", quantity = "3", measurementId = "1"),
                Composition(id = "12", recipeId = "1", ingredientId = "11", quantity = "1", measurementId = "1"),
                Composition(id = "13", recipeId = "1", ingredientId = "6", quantity = "1", measurementId = "2"),
                Composition(id = "14", recipeId = "1", ingredientId = "12", quantity = "1/8", measurementId = "2"),
                Composition(id = "15", recipeId = "1", ingredientId = "13", quantity = "1/2", measurementId = "17")
            ))
        }
    }

    private suspend fun populateIngredients() {
        val ingredients = repository.getIngredients()

        if (ingredients.isEmpty()) {
            repository.addIngredients(listOf(
                Ingredient(id = "1", name = "Carrot"),
                Ingredient(id = "2", name = "Ground meat"),
                Ingredient(id = "3", name = "Onion"),
                Ingredient(id = "4", name = "Olive Oil"),
                Ingredient(id = "5", name = "Garlic"),
                Ingredient(id = "6", name = "Salt"),
                Ingredient(id = "7", name = "Tomato Puree"),
                Ingredient(id = "8", name = "Potato"),
                Ingredient(id = "9", name = "Egg"),
                Ingredient(id = "10", name = "Flour"),
                Ingredient(id = "11", name = "Sour Cream"),
                Ingredient(id = "12", name = "Black Pepper"),
                Ingredient(id = "13", name = "Ground Pork"),
            ))
        }
    }

    private suspend fun populateMeasurements() {
        val measurements = repository.getMeasurements()

        if (measurements.isEmpty()) {
            repository.addMeasurements(listOf(
                Measurement(id = "1", "Tablespoon", "tbsp"),
                Measurement(id = "2", "Teaspoon", "tsp"),
                Measurement(id = "3", "Pinch", "pinch"),
                Measurement(id = "4", "Pinches", "pinches"),
                Measurement(id = "5", "Gram", "gram"),
                Measurement(id = "6", "Unit", "unit"),
                Measurement(id = "7", "Units", "units"),
                Measurement(id = "8", "Cup", "cup"),
                Measurement(id = "9", "Ounce", "oz"),
                Measurement(id = "10", "Milliliter", "mL"),
                Measurement(id = "11", "Stalk", "stalk"),
                Measurement(id = "12", "Clove", "clove"),
                Measurement(id = "13", "Kilogram", "kg"),
                Measurement(id = "14", "Handful", "handful"),
                Measurement(id = "15", "Small jar", "small jar"),
                Measurement(id = "16", "Large jar", "large jar"),
                Measurement(id = "17", "Pound", "pound")
            ))
        }
    }
}