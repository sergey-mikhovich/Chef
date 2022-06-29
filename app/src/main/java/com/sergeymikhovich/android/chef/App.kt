package com.sergeymikhovich.android.chef

import android.app.Application
import com.sergeymikhovich.android.chef.model.*
import com.sergeymikhovich.android.chef.repository.ChefRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
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
                    Category(id = "1", name = "Breakfast"),
                    Category(id = "2", name = "Lunch"),
                    Category(id = "3", name = "Dinner"),
                    Category(id = "4", name = "Appetizer"),
                    Category(id = "5", name = "Salad"),
                    Category(id = "6", name = "Main-course"),
                    Category(id = "7", name = "Side-dish"),
                    Category(id = "8", name = "Baked-good"),
                    Category(id = "9", name = "Dessert"),
                    Category(id = "10", name = "Snack"),
                    Category(id = "11", name = "Soup"),
                    Category(id = "12", name = "Sauce")
                ))
            }
    }

    private suspend fun populateCuisines() {
        val cuisines = repository.getCuisines()

        if (cuisines.isEmpty()) {
            repository.addCuisines(listOf(
                Cuisine(id = "1", name = "Belarusian"),
                Cuisine(id = "2", name = "Russian"),
                Cuisine(id = "3", name = "Italian"),
                Cuisine(id = "4", name = "American"),
                Cuisine(id = "5", name = "Mexican"),
                Cuisine(id = "6", name = "Vietnamese"),
                Cuisine(id = "7", name = "African"),
                Cuisine(id = "8", name = "French"),
                Cuisine(id = "9", name = "Japanese"),
                Cuisine(id = "10", name = "Indian")
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
                    quantityIngredients = 0,
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
                    ),
                    isFavorite = false),
                Recipe(
                    id = "2",
                    name = "Borscht",
                    cuisineId = "2",
                    categoryId = "11",
                    cookingTime = 130,
                    image = "borscht",
                    quantityIngredients = 0,
                    instructions = listOf(
                        CookingStepEntry(id = "1", "Wash meat in cold water, cut into 1 pieces and place in a large " +
                                "soup pot with 14 cups cold water and 1 Tbsp salt. Bring it to a boil and remove the foam as soon " +
                                "as it boils (if you wait, it will be hard to get rid of foam as it integrates into the broth and " +
                                "you'd have to strain it later). Reduce heat, partially cover and simmer 45 minutes - 1 hr, periodically " +
                                "skimming off any foam that rises to the top."),
                        CookingStepEntry(id = "2", "Grate beets on the large grater holes (a food processor works amazingly " +
                                "well). Place them in a large heavy-bottom skillet with 4 Tbsp olive oil and 1 Tbsp vinegar and saute for " +
                                "5 minutes, then reduce heat to med/low and add 1 Tbsp sugar and 2 Tbsp tomato sauce Mix thoroughly and " +
                                "saute until starting to soften, stirring occasionally (about 10 min). Remove from pan and set aside."),
                        CookingStepEntry(id = "3", "In the same skillet (no need to wash it), Saute onion in 1 Tbsp butter for " +
                                "2 min. Add grated carrot and sautee another 5 min or until softened, adding more oil if it seems too dry."),
                        CookingStepEntry(id = "4", "Once the meat has been cooking at least 45 min, place sliced potatoes into."),
                        CookingStepEntry(id = "5", "The soup pot and cook 10 min, then add cabbage, sauteed beets, onion & carrot, " +
                                "and chopped tomatoes. Cook another 10 minutes or until potatoes can be easily pierced with a fork."),
                        CookingStepEntry(id = "6", "Add 2 bay leaves, 1/4 tsp pepper, and more salt to taste (I added another 1/2 tsp salt)."),
                        CookingStepEntry(id = "7", "Chop parsley and pressed garlic then stir them into the soup pot, immediately " +
                                "cover and remove from heat. Let the pot rest covered for 20 minutes for the flavors to meld.")
                    ),
                    isFavorite = false),
                Recipe(
                    id = "3",
                    name = "Slow Cooker Bolognese Sauce",
                    cuisineId = "3",
                    categoryId = "12",
                    cookingTime = 50,
                    image = "bolognese",
                    quantityIngredients = 0,
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
                                "30-40 more minutes until the sauce thickens.")),
                    isFavorite = false),
                Recipe(
                    id = "4",
                    name = "Crispy Buffalo Wings",
                    cuisineId = "4",
                    categoryId = "4",
                    cookingTime = 60,
                    image = "bbq_chicken",
                    quantityIngredients = 0,
                    instructions = listOf(
                        CookingStepEntry(id = "1", "Thoroughly pat dry the chicken with a paper towel. Preheat the " +
                                "oven to 450˚F. Line a rimmed baking sheet with foil and place a wire rack over the pan."),
                        CookingStepEntry(id = "2", "Combine the baking powder, salt and garlic powder in a bowl, " +
                                "sprinkle over the chicken and toss to combine. Arrange chicken on the prepared wire rack."),
                        CookingStepEntry(id = "3", "Bake the chicken for 25 minutes, flip it over and bake for " +
                                "another 25 minutes or until crisp and cooked through."),
                        CookingStepEntry(id = "4", "In a medium-size bowl combine sauce ingredients. Remove chicken " +
                                "from the baking sheet to a bowl. Drizzle the sauce over the chicken. Toss to coat the chicken " +
                                "in the sauce. Serve with your favorite dipping sauce.")
                    ),
                    isFavorite = false),
                Recipe(
                    id = "5",
                    name = "Tostada",
                    cuisineId = "5",
                    categoryId = "6",
                    cookingTime = 30,
                    image = "tostada",
                    quantityIngredients = 0,
                    instructions = listOf(),
                    isFavorite = false),
                Recipe(
                    id = "6",
                    name = "Chocolate Souffle",
                    cuisineId = "8",
                    categoryId = "9",
                    cookingTime = 45,
                    image = "chocolate_souffle",
                    quantityIngredients = 0,
                    instructions = listOf(),
                    isFavorite = false),
            ))
        }
    }

    private suspend fun populateCompositions() {
        val compositions = repository.getCompositions()

        if (compositions.isEmpty()) {
            repository.addCompositions(listOf(
                //Meat Stuffed Potato Pancakes (Draniki)
                Composition(id = "1", recipeId = "1", ingredientId = "8", quantity = "5", measurementId = "7"),
                Composition(id = "2", recipeId = "1", ingredientId = "3", quantity = "1", measurementId = "6"),
                Composition(id = "3", recipeId = "1", ingredientId = "9", quantity = "1", measurementId = "6"),
                Composition(id = "4", recipeId = "1", ingredientId = "10", quantity = "3", measurementId = "1"),
                Composition(id = "5", recipeId = "1", ingredientId = "11", quantity = "1", measurementId = "1"),
                Composition(id = "6", recipeId = "1", ingredientId = "6", quantity = "1", measurementId = "2"),
                Composition(id = "7", recipeId = "1", ingredientId = "12", quantity = "1/8", measurementId = "2"),
                Composition(id = "8", recipeId = "1", ingredientId = "13", quantity = "1/2", measurementId = "17"),

                //Borscht
                Composition(id = "9", recipeId = "2", ingredientId = "14", quantity = "1", measurementId = "17"),
                Composition(id = "10", recipeId = "2", ingredientId = "15", quantity = "14", measurementId = "18"),
                Composition(id = "11", recipeId = "2", ingredientId = "6", quantity = "1", measurementId = "1"),
                Composition(id = "12", recipeId = "2", ingredientId = "16", quantity = "3", measurementId = "7"),
                Composition(id = "13", recipeId = "2", ingredientId = "4", quantity = "4", measurementId = "1"),
                Composition(id = "14", recipeId = "2", ingredientId = "17", quantity = "1", measurementId = "1"),
                Composition(id = "15", recipeId = "2", ingredientId = "18", quantity = "1", measurementId = "1"),
                Composition(id = "16", recipeId = "2", ingredientId = "19", quantity = "2", measurementId = "1"),
                Composition(id = "17", recipeId = "2", ingredientId = "20", quantity = "1", measurementId = "1"),
                Composition(id = "18", recipeId = "2", ingredientId = "3", quantity = "1", measurementId = "9"),
                Composition(id = "19", recipeId = "2", ingredientId = "1", quantity = "2", measurementId = "7"),
                Composition(id = "20", recipeId = "2", ingredientId = "8", quantity = "3", measurementId = "7"),
                Composition(id = "21", recipeId = "2", ingredientId = "21", quantity = "1/2", measurementId = "6"),
                Composition(id = "22", recipeId = "2", ingredientId = "22", quantity = "2", measurementId = "7"),
                Composition(id = "23", recipeId = "2", ingredientId = "23", quantity = "2", measurementId = "7"),
                Composition(id = "24", recipeId = "2", ingredientId = "12", quantity = "1/4", measurementId = "2"),
                Composition(id = "25", recipeId = "2", ingredientId = "24", quantity = "1/4", measurementId = "8"),
                Composition(id = "26", recipeId = "2", ingredientId = "5", quantity = "2", measurementId = "19"),

                //Slow Cooker Bolognese Sauce
                Composition(id = "27", recipeId = "3", ingredientId = "1", quantity = "1", measurementId = "7"),
                Composition(id = "28", recipeId = "3", ingredientId = "2", quantity = "9", measurementId = "9"),
                Composition(id = "29", recipeId = "3", ingredientId = "3", quantity = "2", measurementId = "9"),
                Composition(id = "30", recipeId = "3", ingredientId = "4", quantity = "1/4", measurementId = "8"),
                Composition(id = "31", recipeId = "3", ingredientId = "5", quantity = "1/2", measurementId = "12"),
                Composition(id = "32", recipeId = "3", ingredientId = "6", quantity = "2", measurementId = "4"),
                Composition(id = "33", recipeId = "3", ingredientId = "7", quantity = "9", measurementId = "9"),

                //Crispy Buffalo Wings
                Composition(id = "34", recipeId = "4", ingredientId = "25", quantity = "3", measurementId = "17"),
                Composition(id = "35", recipeId = "4", ingredientId = "26", quantity = "1", measurementId = "1"),
                Composition(id = "36", recipeId = "4", ingredientId = "6", quantity = "1", measurementId = "2"),
                Composition(id = "37", recipeId = "4", ingredientId = "27", quantity = "2", measurementId = "2"),
                Composition(id = "38", recipeId = "4", ingredientId = "20", quantity = "1/4", measurementId = "8"),
                Composition(id = "39", recipeId = "4", ingredientId = "18", quantity = "1", measurementId = "1")
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
                Ingredient(id = "14", name = "Beef"),
                Ingredient(id = "15", name = "Water"),
                Ingredient(id = "16", name = "Beet"),
                Ingredient(id = "17", name = "Vinegar"),
                Ingredient(id = "18", name = "Sugar"),
                Ingredient(id = "19", name = "Tomato sauce"),
                Ingredient(id = "20", name = "Unsalted butter"),
                Ingredient(id = "21", name = "Cabbage"),
                Ingredient(id = "22", name = "Tomato"),
                Ingredient(id = "23", name = "Bay leave"),
                Ingredient(id = "24", name = "Chopped parsley"),
                Ingredient(id = "25", name = "Chicken wings"),
                Ingredient(id = "26", name = "Backing powder"),
                Ingredient(id = "27", name = "Garlic powder")
            ))
        }
    }

    private suspend fun populateMeasurements() {
        val measurements = repository.getMeasurements()

        if (measurements.isEmpty()) {
            repository.addMeasurements(listOf(
                Measurement(id = "1", name = "Tablespoon", abbreviation =  "tbsp"),
                Measurement(id = "2", name = "Teaspoon", abbreviation = "tsp"),
                Measurement(id = "3", name = "Pinch", abbreviation = "pinch"),
                Measurement(id = "4", name = "Pinches", abbreviation = "pinches"),
                Measurement(id = "5", name = "Gram", abbreviation = "gram"),
                Measurement(id = "6", name = "Unit", abbreviation = "unit"),
                Measurement(id = "7", name = "Units", abbreviation = "units"),
                Measurement(id = "8", name = "Cup", abbreviation = "cup"),
                Measurement(id = "9", name = "Ounce", abbreviation = "oz"),
                Measurement(id = "10", name = "Milliliter", abbreviation = "mL"),
                Measurement(id = "11", name = "Stalk", abbreviation = "stalk"),
                Measurement(id = "12", name = "Clove", abbreviation = "clove"),
                Measurement(id = "13", name = "Kilogram", abbreviation = "kg"),
                Measurement(id = "14", name = "Handful", abbreviation = "handful"),
                Measurement(id = "15", name = "Small jar", abbreviation = "small jar"),
                Measurement(id = "16", name = "Large jar", abbreviation = "large jar"),
                Measurement(id = "17", name = "Pound", abbreviation = "pound"),
                Measurement(id = "18", name = "Cups", abbreviation =  "cups"),
                Measurement(id = "19", name = "Cloves", abbreviation = "cloves")
            ))
        }
    }
}