package com.ajaymourya.githubapi.network

import com.squareup.moshi.Json

/**
 * Created by Ajay Mourya on 02,October,2019
 */

data class User(
    val login: String,

    val name: String,

    // used to map avatar_url from the JSON to profileImgUrl in our class
    @Json(name = "avatar_url") val profileImgUrl: String,

    @Json(name = "bio") val summary: String?,

    @Json(name = "public_repos") val noOfRepository: Int)