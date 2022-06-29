package com.sergeymikhovich.android.chef.ui.recipeDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.sergeymikhovich.android.chef.R
import com.sergeymikhovich.android.chef.databinding.ActivityRecipeDetailsBinding
import com.sergeymikhovich.android.chef.ui.decorations.SpacingItemDecoration
import com.sergeymikhovich.android.chef.ui.recipeSteps.RecipeStepsAdapter
import com.sergeymikhovich.android.chef.ui.recipeIngredients.RecipeIngredientsAdapter
import com.sergeymikhovich.android.chef.utils.Constants.SPAN_COUNT_ONE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeDetailsActivity : AppCompatActivity() {

    companion object {
        private const val KEY_RECIPE = "recipeId"

        fun getIntent(context: Context, recipeId: String): Intent {
            return Intent(context, RecipeDetailsActivity::class.java).apply {
                putExtra(KEY_RECIPE, recipeId)
            }
        }
    }

    private lateinit var binding: ActivityRecipeDetailsBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel: RecipeDetailsViewModel by viewModels()
    private val ingredientsAdapter by lazy { RecipeIngredientsAdapter() }
    private val stepsAdapter by lazy { RecipeStepsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadRecipeId()
        setupUI()
    }

    private fun setupUI() {
        initAdapter()
        loadIngredients()
        loadSteps()
        loadRecipeDetails()
        setOnTabSelectedListener()
        showTitleIfToolbarCollapsed()

        binding.toolbarComposition.setNavigationOnClickListener { finish() }
    }

    private fun loadRecipeId() {
        val recipeId = intent.getStringExtra(KEY_RECIPE) ?: return
        viewModel.loadRecipeId(recipeId)
    }

    private fun initAdapter() {
        recyclerView = binding.includedRecipeContent.recyclerViewComposition
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = ingredientsAdapter

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.card_layout_margin)
        recyclerView.addItemDecoration(SpacingItemDecoration(SPAN_COUNT_ONE, spacingInPixels))
    }

    private fun loadIngredients() =
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recipeDetails.observe(this@RecipeDetailsActivity) { recipeDetails ->
                    ingredientsAdapter.submitList(recipeDetails)
                }
            }
        }

    private fun loadSteps() =
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recipe.observe(this@RecipeDetailsActivity) { recipe ->
                    stepsAdapter.submitList(recipe.instructions)
                }
            }
        }

    private fun loadRecipeDetails() =
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recipe.observe(this@RecipeDetailsActivity) { recipe ->
                    binding.includedRecipeContent.recipeNameComposition.text = recipe.name
                    binding.imageComposition.setImageResource(resources.getIdentifier(recipe.uri, null, packageName))
                }
            }
        }

    private fun setOnTabSelectedListener() {
        val tabLayout = binding.includedRecipeContent.tabLayoutComposition

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    when(tab.position) {
                        0 -> recyclerView.adapter = ingredientsAdapter
                        1 -> recyclerView.adapter = stepsAdapter
                        else -> return
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun showTitleIfToolbarCollapsed() {
        var isShow = true
        var scrollRange = -1
        binding.appBarComposition.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout.totalScrollRange
            }
            if (scrollRange + verticalOffset == 0){
                binding.collapsingToolbarLayoutComposition.title = getString(R.string.recipe_details_title_toolbar)
                isShow = true
            } else if (isShow){
                binding.collapsingToolbarLayoutComposition.title = ""
                isShow = false
            }
        })
    }
}