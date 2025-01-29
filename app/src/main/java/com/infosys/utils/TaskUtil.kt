package com.infosys.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.infosys.data.model.CityDetails

object TaskUtil {

    fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    fun parseJsonToModel(jsonString: String): List<CityDetails> {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<List<CityDetails>>() {}.type)
    }
}