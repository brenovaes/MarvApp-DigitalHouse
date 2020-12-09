package com.digitalhouse.br.marvelapp.service


import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryBusca{

    @GET("characters")
    suspend fun getCharactersRepo(
        @Query("offset")ps1:Int,
        @Query("limit")ps2:Int,
        @Query("ts")ps3:String,
        @Query("apikey")ps4:String,
        @Query("hash")ps5:String,
    ): ResCharacters


    @GET("creators")
    suspend fun getCreatorsRepo(
        @Query("offset")ps1:Int,
        @Query("limit")ps2:Int,
        @Query("ts")ps3:String,
        @Query("apikey")ps4:String,
        @Query("hash")ps5:String,
    ): ResCreators


    @GET("comics")
    suspend fun getComicsRepo(
        @Query("offset")ps1:Int,
        @Query("limit")ps2:Int,
        @Query("ts")ps3:String,
        @Query("apikey")ps4:String,
        @Query("hash")ps5:String,
    ): ResComics

}

val urlApiMarvelBusca = "https://gateway.marvel.com/v1/public/"


val retrofitB: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(urlApiMarvelBusca)
    .build()

val serviceB:RepositoryBusca = retrofitB.create(RepositoryBusca::class.java)