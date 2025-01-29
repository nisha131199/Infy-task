package com.infosys.data.domain.datasource

import android.content.Context
import com.infosys.data.model.CityDetails
import com.infosys.utils.TaskUtil
import retrofit2.Response

class DataSource (
    val context: Context,
) {
    fun fetchCitiesList(): Response<List<CityDetails>> {
        val json = TaskUtil.readJsonFromAssets(context, "au_cities.json")
        val citiesResponse = TaskUtil.parseJsonToModel(json)
        return Response.success(citiesResponse)
    }
}