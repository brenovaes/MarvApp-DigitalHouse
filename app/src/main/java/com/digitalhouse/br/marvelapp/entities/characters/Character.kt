package com.digitalhouse.br.marvelapp.entities.characters

import com.digitalhouse.br.marvelapp.entities.comics.Items


data class ResCharacters(
        val code : Int,
        val status : String,
        val copyright : String,
        val attributionText : String,
        val attributionHTML : String,
        val etag : String,
        val data : DataCh
)

data class DataCh(
        val offset : Int,
        val limit : Int,
        val total : Int,
        val count : Int,
        val results : ArrayList<ResultsCh>)

data class ResultsCh (
        val id : Int,
        val name : String,
        val description : String,
        val modified : String,
        val thumbnail : ThumbnailCh,
        val resourceURI : String,
        val comics : ComicsCh,
        val series : SeriesCh,
        val stories : StoriesCh,
        val events : EventsCh,
        val urls : ArrayList<UrlsCh>
)


data class ComicsCh (
        val available : Int,
        val collectionURI : String,
        val items : ArrayList<Items?>,
        val returned : Int
)



data class EventsCh (
        val available : Int,
        val collectionURI : String,
        val items : ArrayList<Items?>,
        val returned : Int
)

//data class ItemsCh (
//        val resourceURI : String,
//        val name : String
//)

data class SeriesCh (
        val available : Int,
        val collectionURI : String,
        val items : ArrayList<Items?>,
        val returned : Int
)

data class StoriesCh (
        val available : Int,
        val collectionURI : String,
        val items : ArrayList<Items?>,
        val returned : Int
)

data class ThumbnailCh (
        val path : String,
        val extension : String
)

data class UrlsCh (
        val type : String,
        val url : String
)
