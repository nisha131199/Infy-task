package com.infosys.data.domain.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infosys.data.resources.Resource
import com.infosys.data.domain.repository.Repository
import com.infosys.data.model.CityDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    val application: Application,
    val repository: Repository
): ViewModel() {
    private var _response = MutableStateFlow<Resource<List<CityDetails>?>>(Resource.Loading())
    val response: StateFlow<Resource<List<CityDetails>?>> = _response

    private var _responseHasReverse = MutableStateFlow(false)
    val responseHasReverse: StateFlow<Boolean> = _responseHasReverse

    fun fetchCitiesList() {
        viewModelScope.launch {
            _response.value= Resource.Loading()
            repository.fetchCitiesList()
                .catch {
                    _response.value = Resource.Error(it.message.toString())
                }
                .collect {
                    _response.value = Resource.Success(
                        if (responseHasReverse.value) {
                            _responseHasReverse.value = false
                            it.data?.reversed()
                        }
                        else {
                            _responseHasReverse.value = true
                            it.data
                        }
                    )
                }
        }
    }
}