package com.countries.app.domain

import com.countries.CountriesQuery

class GetCountriesUseCase(
    private val countryClient: CountryClient
) {

    suspend fun execute(text : String = "") : List<CountriesQuery.Country> {
        return countryClient.getCountries()
            .sortedBy { it.name }
            .filter { it.name.contains(text, true) }
    }

}