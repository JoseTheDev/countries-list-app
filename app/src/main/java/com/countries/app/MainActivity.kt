package com.countries.app

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.countries.CountriesQuery
import com.countries.app.adapter.CountriesAdapter
import com.countries.app.presentation.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recycler_countries)
        val loading = findViewById<ProgressBar>(R.id.loading_countries)

        val countriesViewModel = ViewModelProvider(this)[CountriesViewModel::class.java]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                countriesViewModel.state.collect { state ->
                    if (state.isLoading) {
                        loading.visibility = View.VISIBLE
                    } else {
                        val adapterCountries = CountriesAdapter(state.countries)
                        recycler.adapter = adapterCountries

                        loading.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}