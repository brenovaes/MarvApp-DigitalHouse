package com.digitalhouse.br.marvelapp.service


import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import com.digitalhouse.br.marvelapp.entities.events.ResEvents
import com.digitalhouse.br.marvelapp.entities.series.ResSeries
import com.digitalhouse.br.marvelapp.entities.stories.ResStories
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryCreators {

    @GET("creators/{id}")
    suspend fun getCreatorRepo(
            @Path("id") id: Int,
            @Query("offset") ps1: Int,
            @Query("limit") ps2: Int,
            @Query("ts") ps3: String,
            @Query("apikey") ps4: String,
            @Query("hash") ps5: String
    ): ResCreators


    @GET("creators/{id}/comics")
    suspend fun getCreatorComicsRepo(
            @Path("id") id: Int,
            @Query("offset") ps1: Int,
            @Query("limit") ps2: Int,
            @Query("ts") ps3: String,
            @Query("apikey") ps4: String,
            @Query("hash") ps5: String
    ): ResComics


    @GET("creators/{id}/series")
    suspend fun getCreatorSeriesRepo(
            @Path("id") id: Int,
            @Query("offset") ps1: Int,
            @Query("limit") ps2: Int,
            @Query("ts") ps3: String,
            @Query("apikey") ps4: String,
            @Query("hash") ps5: String,
    ): ResSeries

    @GET("creators/{id}/events")
    suspend fun getCreatorEventsRepo(
            @Path("id") id: Int,
            @Query("offset") ps1: Int,
            @Query("limit") ps2: Int,
            @Query("ts") ps3: String,
            @Query("apikey") ps4: String,
            @Query("hash") ps5: String,
    ): ResEvents


    @GET("creators/{id}/stories")
    suspend fun getCreatorStoriesRepo(
            @Path("id") id: Int,
            @Query("offset") ps1: Int,
            @Query("limit") ps2: Int,
            @Query("ts") ps3: String,
            @Query("apikey") ps4: String,
            @Query("hash") ps5: String,
    ): ResStories


}

val urlApiMarvelCreator = "https://gateway.marvel.com/v1/public/"


val retrofitCreator = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(urlApiMarvelCreator)
        .build()


val serviceCr: RepositoryCreators = retrofitCreator.create(RepositoryCreators::class.java)

