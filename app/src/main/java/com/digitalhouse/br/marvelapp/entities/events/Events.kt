package com.digitalhouse.br.marvelapp.entities.events

data class ResEvents(

    val code : Int,
    val status : String,
    val copyright : String,
    val attributionText : String,
    val attributionHTML : String,
    val etag : String,
    val data : DataEv
)

data class DataEv (

    val offset : Int,
    val limit : Int,
    val total : Int,
    val count : Int,
    val results : ArrayList<ResultsEv>
)

data class ResultsEv(

    val id : Int,
    val title : String,
    val description : String,
    val resourceURI : String,
    val urls : ArrayList<UrlsEv>,
    val modified : String,
    val start : String,
    val end : String,
    val thumbnail : ThumbnailEv,
    val creators : CreatorsEv,
    val characters : CharactersEv,
    val stories : StoriesEv,
    val comics : ComicsEv,
    val series : SeriesEv,
    val next : NextEv,
    val previous : PreviousEv
)


data class PreviousEv (

    val resourceURI : String,
    val name : String
)

data class NextEv (

    val resourceURI : String,
    val name : String
)

data class ItemsEv (

    val resourceURI : String,
    val name : String
)

data class CreatorsEv (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<ItemsEv>,
    val returned : Int
)

data class ComicsEv (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<ItemsEv>,
    val returned : Int
)

data class CharactersEv (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<ItemsEv>,
    val returned : Int
)

data class ThumbnailEv (

    val path : String,
    val extension : String
)

data class StoriesEv (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<ItemsEv>,
    val returned : Int
)
data class SeriesEv (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<ItemsEv>,
    val returned : Int
)

data class UrlsEv (

    val type : String,
    val url : String
)
