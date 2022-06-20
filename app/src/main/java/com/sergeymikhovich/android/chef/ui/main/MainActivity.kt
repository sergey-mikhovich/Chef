package com.sergeymikhovich.android.chef.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sergeymikhovich.android.chef.R
import com.sergeymikhovich.android.chef.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setSupportActionBar(binding.mainToolbar)
    }

    private fun setBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        val bottomNavigationView = binding.bottomNavigationMain
        val navigationItemSelectedListener = NavigationItemSelectedListener(supportFragmentManager)

        bottomNavigationView.setOnItemSelectedListener(navigationItemSelectedListener)
        bottomNavigationView.selectedItemId = R.id.allRecipes
    }
}