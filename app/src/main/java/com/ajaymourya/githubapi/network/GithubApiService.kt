package com.ajaymourya.githubapi.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Ajay Mourya on 02,October,2019
 */

private const val BASE_URL = " https://api.github.com/users/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface GithubApiService {

    @GET("{user}")
    fun getFeed(@Path("user") user: String):
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<User>

    @GET("{user}/repos")
    fun getRepos(@Path("user") user: String):
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<List<Repository>>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object GithubApi {
    val retrofitService: GithubApiService by lazy { retrofit.create(GithubApiService::class.java) }
}