package com.digitalhouse.br.marvelapp.service

import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import com.digitalhouse.br.marvelapp.entities.sugest.ResSugestao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositorySugestao{
    @GET("characters")
    suspend fun getCharactersRepoS(
        @Query("offset")ps1:Int,
        @Query("limit")ps2:Int,
        @Query("ts")ps3:String,
        @Query("apikey")ps4:String,
        @Query("hash")ps5:String,
    ): ResCharacters


    @GET("creators")
    suspend fun getCreatorsRepoS(
        @Query("offset")ps1:Int,
        @Query("limit")ps2:Int,
        @Query("ts")ps3:String,
        @Query("apikey")ps4:String,
        @Query("hash")ps5:String,
    ): ResCreators


    @GET("comics")
    suspend fun getComicsRepoS(
        @Query("offset")ps1:Int,
        @Query("limit")ps2:Int,
        @Query("ts")ps3:String,
        @Query("apikey")ps4:String,
        @Query("hash")ps5:String,
    ): ResComics

}

val urlApiMarvelSugestao = "https://gateway.marvel.com/v1/public/"


val retrofitS: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(urlApiMarvelSugestao)
    .build()

val serviceS:RepositorySugestao = retrofitS.create(RepositorySugestao::class.java)
