package com.countries.app.data

import com.apollographql.apollo3.ApolloClient
import com.countries.CountriesQuery
import com.countries.app.domain.CountryClient

class ApolloCountryClient(
    private val apolloClient: ApolloClient
): CountryClient {
    override suspend fun getCountries(): List<CountriesQuery.Country> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?: emptyList()
    }
}