package com.digitalhouse.br.marvelapp.entities.series

import com.digitalhouse.br.marvelapp.entities.comics.Items

data class ResSeries (

    val code : Int,
    val status : String,
    val copyright : String,
    val attributionText : String,
    val attributionHTML : String,
    val etag : String,
    val data : DataSe
)

data class DataSe (

    val offset : Int,
    val limit : Int,
    val total : Int,
    val count : Int,
    val results : ArrayList<ResultsSe>
)

data class ResultsSe (

    val id : Int,
    val title : String,
    val description : String,
    val resourceURI : String,
    val urls : ArrayList<UrlsSe>,
    val startYear : Int,
    val endYear : Int,
    val rating : String,
    val type : String,
    val modified : String,
    val thumbnail : ThumbnailSe,
    val creators : CreatorsSe,
    val characters : CharactersSe,
    val stories : StoriesSe,
    val comics : ComicsSe,
    val events : EventsSe,
    val next : String,
    val previous : String
)


data class CreatorsSe (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<Items>,
    val returned : Int
)

data class CharactersSe (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<Items>,
    val returned : Int
)

data class StoriesSe (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<Items>,
    val returned : Int
)

data class ComicsSe(

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<Items>,
    val returned : Int
)

data class EventsSe (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<String>,
    val returned : Int
)

data class UrlsSe (

    val type : String,
    val url : String
)

data class ThumbnailSe (

    val path : String,
    val extension : String
)


data class ItemsSe (

    val resourceURI : String,
    val name : String
)