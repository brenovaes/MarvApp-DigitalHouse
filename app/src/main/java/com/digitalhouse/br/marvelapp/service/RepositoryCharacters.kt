package com.digitalhouse.br.marvelapp.service


import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryCharacters {

    @GET("characters")
    suspend fun getAllCharacterRepo(
        @Query("offset") ps1: Int,
        @Query("limit") ps2: Int,
        @Query("ts") ps3: String,
        @Query("apikey") ps4: String,
        @Query("hash") ps5: String
    ):  ResCharacters

    @GET("characters/{id}")
    suspend fun getCharacterRepo(
        @Path("id") id: Int,
        @Query("offset") ps1: Int,
        @Query("limit") ps2: Int,
        @Query("ts") ps3: String,
        @Query("apikey") ps4: String,
        @Query("hash") ps5: String
    ):  ResCharacters


    @GET("characters/{id}/comics")
    suspend fun getCharacterComicsRepo(
            @Path("id") id: Int,
            @Query("offset") ps1: Int,
            @Query("limit") ps2: Int,
            @Query("ts") ps3: String,
            @Query("apikey") ps4: String,
            @Query("hash") ps5: String,
    ): ResComics

    @GET("characters/{id}/series")
    suspend fun getCharacterSeriesRepo(
            @Path("id") id: Int,
            @Query("offset") ps1: Int,
            @Query("limit") ps2: Int,
            @Query("ts") ps3: String,
            @Query("apikey") ps4: String,
            @Query("hash") ps5: String,
    ): ResCharacters

    @GET("characters/{id}/events")
    suspend fun getCharacterEventsRepo(
            @Path("id") id: Int,
            @Query("offset") ps1: Int,
            @Query("limit") ps2: Int,
            @Query("ts") ps3: String,
            @Query("apikey") ps4: String,
            @Query("hash") ps5: String,
    ): ResCharacters


    @GET("characters/{id}/stories")
    suspend fun getCharacterStoriesRepo(
            @Path("id") id: Int,
            @Query("offset") ps1: Int,
            @Query("limit") ps2: Int,
            @Query("ts") ps3: String,
            @Query("apikey") ps4: String,
            @Query("hash") ps5: String,
    ): ResCharacters


}

val urlApiMarvelCharacter = "https://gateway.marvel.com/v1/public/"


val retrofitCharacter = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(urlApiMarvelCharacter)
        .build()

val serviceCh: RepositoryCharacters = retrofitCharacter.create(RepositoryCharacters::class.java)