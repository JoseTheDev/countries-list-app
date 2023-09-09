package com.countries.app.domain

import com.countries.CountriesQuery

interface CountryClient {
    suspend fun getCountries(): List<CountriesQuery.Country>
}