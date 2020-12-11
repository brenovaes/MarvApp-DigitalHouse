package com.digitalhouse.br.marvelapp.entities.stories


data class ResStories (

    val code : Int,
    val status : String,
    val copyright : String,
    val attributionText : String,
    val attributionHTML : String,
    val etag : String,
    val data : DataSt
)

data class ResultsSt (

    val id : Int,
    val title : String,
    val description : String,
    val resourceURI : String,
    val type : String,
    val modified : String,
    val thumbnail : String,
    val creators : CreatorsSt,
    val characters : CharactersSt,
    val series : SeriesSt,
    val comics : ComicsSt,
    val events : EventsSt,
    val originalIssue : OriginalIssueSt
)

data class SeriesSt (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<ItemsSt>,
    val returned : Int
)

data class OriginalIssueSt (

    val resourceURI : String,
    val name : String
)

data class ItemsSt (

    val resourceURI : String,
    val name : String
)

data class EventsSt (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<ItemsSt>,
    val returned : Int
)

data class DataSt (

    val offset : Int,
    val limit : Int,
    val total : Int,
    val count : Int,
    val results : ArrayList<ResultsSt>
)

data class CreatorsSt (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<ItemsSt>,
    val returned : Int
)

data class ComicsSt (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<ItemsSt>,
    val returned : Int
)

data class CharactersSt (

    val available : Int,
    val collectionURI : String,
    val items : ArrayList<ItemsSt>,
    val returned : Int
)