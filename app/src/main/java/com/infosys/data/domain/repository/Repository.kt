package com.infosys.data.domain.repository

import com.infosys.MyApplication
import com.infosys.data.resources.Resource
import com.infosys.data.domain.datasource.DataSource
import com.infosys.data.model.CityDetails
import com.infosys.data.model.ErrorResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository (private val dataSource: DataSource) {
    fun fetchCitiesList(): Flow<Resource<List<CityDetails>>> {
        return flow {
            val response = dataSource.fetchCitiesList()
            if(response.isSuccessful){
                response.body()?.let {result->
                    emit(
                        Resource.Success(
                            result
                        )
                    )
                }
            }
            else {
                emit(
                    Resource.Error(
                        MyApplication.gson.fromJson(
                            response.errorBody()?.charStream(),
                            ErrorResponse::class.java
                        ).message
                    )
                )
            }
        }
    }
}