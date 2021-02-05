package com.digitalhouse.br.marvelapp.service


import com.digitalhouse.br.marvelapp.apikeyT
import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import com.digitalhouse.br.marvelapp.hashT
import com.digitalhouse.br.marvelapp.tsT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryBusca{

    @GET("characters")
    suspend fun getCharactersBuscaRepo(
        @Query("offset")ps1:Int,
        @Query("limit")ps2:Int,
        @Query("ts") ps3: String = tsT,
        @Query("apikey") ps4: String = apikeyT,
        @Query("hash") ps5: String = hashT,
        @Query("nameStartsWith")ps6:String
    ): ResCharacters

    @GET("characters")
    suspend fun getCharactersBuscaRepo(
        @Query("offset")ps1:Int,
        @Query("limit")ps2:Int,
        @Query("ts") ps3: String = tsT,
        @Query("apikey") ps4: String = apikeyT,
        @Query("hash") ps5: String = hashT
    ): ResCharacters


    @GET("creators")
    suspend fun getCreatorsRepo(
        @Query("offset")ps1:Int,
        @Query("limit")ps2:Int,
        @Query("ts") ps3: String = tsT,
        @Query("apikey") ps4: String = apikeyT,
        @Query("hash") ps5: String = hashT,
        @Query("nameStartsWith")ps6:String

    ): ResCreators

    @GET("creators")
    suspend fun getCreatorsRepo(
        @Query("offset")ps1:Int,
        @Query("limit")ps2:Int,
        @Query("ts") ps3: String = tsT,
        @Query("apikey") ps4: String = apikeyT,
        @Query("hash") ps5: String = hashT
    ): ResCreators


    @GET("comics")
    suspend fun getComicsRepo(
        @Query("offset")ps1:Int,
        @Query("limit")ps2:Int,
        @Query("ts") ps3: String = tsT,
        @Query("apikey") ps4: String = apikeyT,
        @Query("hash") ps5: String = hashT,
        @Query("titleStartsWith")ps6:String
    ): ResComics

    @GET("comics")
    suspend fun getComicsRepo(
        @Query("offset")ps1:Int,
        @Query("limit")ps2:Int,
        @Query("ts") ps3: String = tsT,
        @Query("apikey") ps4: String = apikeyT,
        @Query("hash") ps5: String = hashT,
    ): ResComics

}

val urlApiMarvelBusca = "https://gateway.marvel.com/v1/public/"


val retrofitB: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(urlApiMarvelBusca)
    .build()

val serviceB:RepositoryBusca = retrofitB.create(RepositoryBusca::class.java)

