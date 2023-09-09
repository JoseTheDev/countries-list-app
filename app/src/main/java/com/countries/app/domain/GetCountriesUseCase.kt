package com.countries.app.domain

import com.countries.CountriesQuery

class GetCountriesUseCase(
    private val countryClient: CountryClient
) {

    suspend fun execute(): List<CountriesQuery.Country> {
        return countryClient.getCountries()
            .sortedBy { it.name }
    }

}