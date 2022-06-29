package com.sergeymikhovich.android.chef.ui.recipies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergeymikhovich.android.chef.R
import com.sergeymikhovich.android.chef.databinding.SearchRecipesFragmentBinding
import com.sergeymikhovich.android.chef.model.Resource
import com.sergeymikhovich.android.chef.ui.decorations.SpacingItemDecoration
import com.sergeymikhovich.android.chef.ui.recipeDetails.RecipeDetailsActivity
import com.sergeymikhovich.android.chef.ui.recipies.adapter.SearchRecipesAdapter
import com.sergeymikhovich.android.chef.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchRecipesFragment : Fragment() {

    companion object {
        fun newInstance() = SearchRecipesFragment()
    }

    private val TAG = "SearchRecipesFragment"
    private val viewModel: RecipesViewModel by viewModels()
    private val adapter by lazy { SearchRecipesAdapter() }
    private var _binding: SearchRecipesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchRecipesFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupRecipeSearch()
    }

    private fun setupUI() {
        (activity as AppCompatActivity).supportActionBar?.title = context?.getString(R.string.search_recipes_title)

        val recipesRecyclerView = binding.searchRecipesRecyclerView

        recipesRecyclerView.adapter = adapter
        recipesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.card_layout_margin)
        recipesRecyclerView.addItemDecoration(SpacingItemDecoration(Constants.SPAN_COUNT_ONE, spacingInPixels))

        var job: Job? = null
        binding.searchRecipesEditText.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }
    }

    private fun setupRecipeSearch() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchRecipes.observe(viewLifecycleOwner) { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { recipeSearchResponse ->
                                adapter.submitList(recipeSearchResponse.results)
                            }
                        }
                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                Log.e(TAG, "An error occured: $message")
                            }
                        }
                        is Resource.Loading -> {
                            showProgressBar()
                        }
                    }
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun showRecipeDetails(recipeId: String) {
        val intent = RecipeDetailsActivity.getIntent(requireActivity(), recipeId)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}