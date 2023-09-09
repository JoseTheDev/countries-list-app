package com.countries.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.countries.CountriesQuery
import com.countries.app.domain.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }

            _state.update { it.copy(
                countries = getCountriesUseCase.execute(),
                isLoading = false
            ) }
        }
    }
}

data class CountriesState(
    val countries: List<CountriesQuery.Country> = emptyList(),
    val isLoading: Boolean = false
)