package com.infosys.data.model

import com.google.gson.annotations.SerializedName

data class CityDetails(
    var city: String? = null,
    var lat: String? = null,
    var lng: String? = null,
    var country: String? = null,
    var iso2: String? = null,
    @SerializedName("admin_name")
    var adminName: String? = null,
    var capital: String? = null,
    var population: String? = null,
    @SerializedName("population_proper")
    var populationProper : String? = null
)