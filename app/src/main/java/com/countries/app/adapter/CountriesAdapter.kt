package com.countries.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.countries.CountriesQuery
import com.countries.app.R

class CountriesAdapter(
    private val countries: List<CountriesQuery.Country>
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_row_country, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country: CountriesQuery.Country = countries[position]

        holder.tvCountryName.text = "${country.emoji} ${country.name}"
        holder.tvCountryContinent.text = country.continent.name
    }

    override fun getItemCount() = countries.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCountryName: TextView
        val tvCountryContinent: TextView

        init {
            tvCountryName = view.findViewById(R.id.tv_country_name)
            tvCountryContinent = view.findViewById(R.id.tv_country_continent)
        }
    }

}