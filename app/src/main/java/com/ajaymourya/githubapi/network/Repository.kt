package com.ajaymourya.githubapi.network

import com.squareup.moshi.Json

/**
 * Created by Ajay Mourya on 02,October,2019
 */

data class Repository(

    val name: String,

    @Json(name = "full_name") val repoUrl: String)