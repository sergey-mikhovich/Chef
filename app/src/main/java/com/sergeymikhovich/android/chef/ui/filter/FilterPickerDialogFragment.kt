package com.sergeymikhovich.android.chef.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sergeymikhovich.android.chef.R
import com.sergeymikhovich.android.chef.databinding.DialogFilterRecipesBinding
import com.sergeymikhovich.android.chef.ui.recipies.RecipesViewModel
import kotlinx.coroutines.launch

class FilterPickerDialogFragment(
    private val onFilterSelected: (Filter?) -> Unit
) : DialogFragment() {

    private var _binding: DialogFilterRecipesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogFilterRecipesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        initCategories()
        initCuisines()

        binding.noFilter.isChecked = true
        binding.filterOptions.setOnCheckedChangeListener { _, checkedId ->
            updateOptions(checkedId)
        }

        binding.selectButton.setOnClickListener {
            filterRecipes()
        }
    }

    private fun updateOptions(checkedId: Int) {
        when (checkedId) {
            binding.noFilter.id -> {
                binding.categorySpinner.visibility = View.GONE
                binding.cuisineSpinner.visibility = View.GONE
            }
            binding.filterByCuisine.id -> {
                binding.categorySpinner.visibility = View.GONE
                binding.cuisineSpinner.visibility = View.VISIBLE
            }
            binding.filterByCategory.id -> {
                binding.categorySpinner.visibility = View.VISIBLE
                binding.cuisineSpinner.visibility = View.GONE
            }
        }
    }

    private fun initCategories() {
        viewModel.loadCategories()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categories.observe(viewLifecycleOwner) { categories ->
                    binding.categorySpinner.adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        categories.map { it.name }.sortedBy { it })
                }
            }
        }
    }

    private fun initCuisines() {
        viewModel.loadCuisines()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cuisines.observe(viewLifecycleOwner) { cuisines ->
                    binding.cuisineSpinner.adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        cuisines.map { it.name }.sortedBy { it })
                }
            }
        }
    }

    private fun filterRecipes() =
        viewLifecycleOwner.lifecycleScope.launch {
            val selectedCategoryId = viewModel.categories.value?.firstOrNull { category ->
                category.name == binding.categorySpinner.selectedItem
            }?.id

            val selectedCuisineId = viewModel.cuisines.value?.firstOrNull { cuisine ->
                cuisine.name == binding.cuisineSpinner.selectedItem
            }?.id

            if (selectedCategoryId == null && binding.filterOptions.checkedRadioButtonId == R.id.filterByCategory) {
                return@launch
            }

            if (selectedCuisineId == null && binding.filterOptions.checkedRadioButtonId == R.id.filterByCuisine) {
                return@launch
            }

            val filter = when (binding.filterOptions.checkedRadioButtonId) {
                R.id.filterByCategory ->Filter.ByCategory(selectedCategoryId ?: "")
                R.id.filterByCuisine -> Filter.ByCuisine(selectedCuisineId ?: "")
                else -> null
            }

            onFilterSelected(filter)
            dismissAllowingStateLoss()
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}