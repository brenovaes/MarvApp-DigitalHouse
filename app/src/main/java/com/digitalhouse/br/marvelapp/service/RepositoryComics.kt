package com.digitalhouse.br.marvelapp.service

import com.digitalhouse.br.marvelapp.apikeyT
import com.digitalhouse.br.marvelapp.entities.events.ResEvents
import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import com.digitalhouse.br.marvelapp.entities.series.ResSeries
import com.digitalhouse.br.marvelapp.entities.stories.ResStories
import com.digitalhouse.br.marvelapp.hashT
import com.digitalhouse.br.marvelapp.tsT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryComics{

    @GET("comics/{id}")
    suspend fun getComicRepo(
        @Path("id")id:Int,
        @Query("offset")ps1:Int,
        @Query("limit")ps2:Int,
        @Query("ts") ps3: String = tsT,
        @Query("apikey") ps4: String = apikeyT,
        @Query("hash") ps5: String = hashT
    ): ResComics


    @GET("comics/{id}/characters")
    suspend fun getComicCharactersRepo(
            @Path("id")id:Int,
            @Query("offset")ps1:Int,
            @Query("limit")ps2:Int,
            @Query("ts") ps3: String = tsT,
            @Query("apikey") ps4: String = apikeyT,
            @Query("hash") ps5: String = hashT
    ): ResCharacters

    @GET("series/{id}")
    suspend fun getComicSeriesRepo(
            @Path("id")id:Int,
            @Query("offset")ps1:Int,
            @Query("limit")ps2:Int,
            @Query("ts") ps3: String = tsT,
            @Query("apikey") ps4: String = apikeyT,
            @Query("hash") ps5: String = hashT
    ): ResSeries

    @GET("comics/{id}/events")
    suspend fun getComicEventsRepo(
            @Path("id")id:Int,
            @Query("offset")ps1:Int,
            @Query("limit")ps2:Int,
            @Query("ts") ps3: String = tsT,
            @Query("apikey") ps4: String = apikeyT,
            @Query("hash") ps5: String = hashT
    ): ResEvents


    @GET("comics/{id}/stories")
    suspend fun getComicStoriesRepo(
            @Path("id")id:Int,
            @Query("offset")ps1:Int,
            @Query("limit")ps2:Int,
            @Query("ts") ps3: String = tsT,
            @Query("apikey") ps4: String = apikeyT,
            @Query("hash") ps5: String = hashT
    ): ResStories

    @GET("comics/{id}/creators")
    suspend fun getComicCreatorsRepo(
            @Path("id")id:Int,
            @Query("offset")ps1:Int,
            @Query("limit")ps2:Int,
            @Query("ts") ps3: String = tsT,
            @Query("apikey") ps4: String = apikeyT,
            @Query("hash") ps5: String = hashT
    ): ResCreators


}

val urlApiMarvelComic = "https://gateway.marvel.com/v1/public/"


val retrofitComic = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(urlApiMarvelComic)
        .build()

val serviceCo:RepositoryComics = retrofitComic.create(RepositoryComics::class.java)