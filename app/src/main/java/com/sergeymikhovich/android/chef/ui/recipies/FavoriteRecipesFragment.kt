package com.sergeymikhovich.android.chef.ui.recipies

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergeymikhovich.android.chef.R
import com.sergeymikhovich.android.chef.databinding.FavoriteRecipesFragmentBinding
import com.sergeymikhovich.android.chef.ui.decorations.SpacingItemDecoration
import com.sergeymikhovich.android.chef.ui.filter.ByCategory
import com.sergeymikhovich.android.chef.ui.filter.ByCuisine
import com.sergeymikhovich.android.chef.ui.filter.Filter
import com.sergeymikhovich.android.chef.ui.filter.FilterPickerDialogFragment
import com.sergeymikhovich.android.chef.ui.recipeDetails.RecipeDetailsActivity
import com.sergeymikhovich.android.chef.ui.recipies.adapter.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val SPAN_COUNT = 1

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteRecipesFragment()
    }

    private var _binding: FavoriteRecipesFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecipesViewModel by viewModels()
    private val adapter by lazy { RecipesAdapter(::showRecipeDetails, viewModel::onFavoriteClick) }
    private var filter: Filter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavoriteRecipesFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        loadFavoriteRecipes()
    }

    private fun setupUI() {
        (activity as AppCompatActivity).supportActionBar?.title =
            context?.getString(R.string.favorites_title)

        val recyclerView = binding.recyclerViewFavoriteRecipes
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.card_layout_margin)
        recyclerView.addItemDecoration(SpacingItemDecoration(SPAN_COUNT, spacingInPixels))
    }

    private fun loadFavoriteRecipes() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recipeDetailsFlow.collect { recipeDetails ->

                    val filteredFavoriteRecipes = when (filter) {
                        is ByCategory -> recipeDetails.filter { it.category.id == (filter as ByCategory).categoryId }
                        is ByCuisine -> recipeDetails.filter { it.cuisine.id == (filter as ByCuisine).cuisineId }
                        else -> recipeDetails
                    }.filter { it.recipe.isFavorite }

                    adapter.submitList(filteredFavoriteRecipes)
                }
            }
        }
    }

    private fun showRecipeDetails(recipeId: String) {
        val intent = RecipeDetailsActivity.getIntent(requireActivity(), recipeId)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter -> {
                val dialog = FilterPickerDialogFragment { filter ->
                    this.filter = filter
                    loadFavoriteRecipes()
                }
                dialog.show(childFragmentManager, null)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}