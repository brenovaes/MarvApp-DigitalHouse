package com.digitalhouse.br.marvelapp.service

import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryComics{

    @GET("comics")
    suspend fun getAllComicsRepo(
            @Query("offset")ps1:Int,
            @Query("limit")ps2:Int,
            @Query("ts")ps3:String,
            @Query("apikey")ps4:String,
            @Query("hash")ps5:String,
    ): ResComics


    @GET("comics/{id}/characters")
    suspend fun getComicCharactersRepo(
            @Path("id")id:Int,
            @Query("offset")ps1:Int,
            @Query("limit")ps2:Int,
            @Query("ts")ps3:String,
            @Query("apikey")ps4:String,
            @Query("hash")ps5:String,
    ): ResComics

    @GET("comics/{id}/series")
    suspend fun getComicSeriesRepo(
            @Path("id")id:Int,
            @Query("offset")ps1:Int,
            @Query("limit")ps2:Int,
            @Query("ts")ps3:String,
            @Query("apikey")ps4:String,
            @Query("hash")ps5:String,
    ): ResComics

    @GET("comics/{id}/events")
    suspend fun getComicEventsRepo(
            @Path("id")id:Int,
            @Query("offset")ps1:Int,
            @Query("limit")ps2:Int,
            @Query("ts")ps3:String,
            @Query("apikey")ps4:String,
            @Query("hash")ps5:String,
    ): ResComics


    @GET("comics/{id}/stories")
    suspend fun getComicStoriesRepo(
            @Path("id")id:Int,
            @Query("offset")ps1:Int,
            @Query("limit")ps2:Int,
            @Query("ts")ps3:String,
            @Query("apikey")ps4:String,
            @Query("hash")ps5:String,
    ): ResComics

    @GET("comics/{id}/creators")
    suspend fun getComicCreatorsRepo(
            @Path("id")id:Int,
            @Query("offset")ps1:Int,
            @Query("limit")ps2:Int,
            @Query("ts")ps3:String,
            @Query("apikey")ps4:String,
            @Query("hash")ps5:String,
    ): ResComics


}

val urlApiMarvelComic = "https://gateway.marvel.com/v1/public/"


val retrofitComic = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(urlApiMarvelComic)
        .build()

val serviceCo:RepositoryComics = retrofitComic.create(RepositoryComics::class.java)