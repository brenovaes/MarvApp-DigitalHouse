package com.digitalhouse.br.marvelapp.entities.creators




data class ResCreators(
        val code : Int,
        val status : String,
        val copyright : String,
        val attributionText : String,
        val attributionHTML : String,
        val etag : String,
        val data: DataCr
)

data class DataCr(
        val offset : Int,
        val limit : Int,
        val total : Int,
        val count : Int,
        val results : ArrayList<ResultsCr>)


data class ResultsCr(

        val id : Int,
        val fullName : String,
        val modified : String,
        val thumbnail : ThumbnailCr,
        val resourceURI : String,
        val comics : ComicsCr,
        val series: SeriesCr,
        val stories : StoriesCr,
        val events : EventsCr,
        val urls: ArrayList<UrlsCr>

)

data class ComicsCr (

        val available : Int,
        val collectionURI : String,
        var items: ArrayList<ItemsCr?>,
        val returned : Int
)

data class SeriesCr (

        val available : Int,
        val collectionURI : String,
        val items : ArrayList<ItemsCr?>,
        val returned : Int
)


data class EventsCr (

        val available : Int,
        val collectionURI : String,
        val items : ArrayList<ItemsCr?>,
        val returned : Int
)

data class StoriesCr (

        val available : Int,
        val collectionURI : String,
        val items : ArrayList<ItemsCr?>,
        val returned : Int
)



data class ItemsCr (

        val resourceURI : String,
        val name : String
)


data class ThumbnailCr (

        val path : String,
        val extension : String
)

data class UrlsCr (

        val type : String,
        val url : String
)

