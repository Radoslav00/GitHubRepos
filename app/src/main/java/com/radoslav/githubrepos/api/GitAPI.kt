package com.radoslav.githubrepos.api

import com.radoslav.githubrepos.BuildConfig
import com.radoslav.githubrepos.ext.decode
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface GitAPI {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @Headers(
        "Accept: application/vnd.github.v3+json"
    )
    @GET("search/repositories")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): GitResponse
}