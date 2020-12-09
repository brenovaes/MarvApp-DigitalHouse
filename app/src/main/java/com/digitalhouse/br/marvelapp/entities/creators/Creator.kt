package com.digitalhouse.br.marvelapp.entities.creators



data class ResCreators(
        val code : Int,
        val status : String,
        val copyright : String,
        val attributionText : String,
        val attributionHTML : String,
        val etag : String,
        val data: Data
)

data class Data(
        val offset : Int,
        val limit : Int,
        val total : Int,
        val count : Int,
        val results : ArrayList<Results>)


data class Results(

        val id : Int,
        val fullName : String,
        val modified : String,
        val thumbnail : Thumbnail,
        val resourceURI : String,
        val comics : Comics,
        val series: Series,
        val stories : Stories,
        val events : Events,
        val urls: List<Urls>

)

data class Comics (

        val available : Int,
        val collectionURI : String,
        val items: List<Items>,
        val returned : Int
)

data class Series (

        val available : Int,
        val collectionURI : String,
        val items : List<Items>,
        val returned : Int
)


data class Events (

        val available : Int,
        val collectionURI : String,
        val items : List<String>,
        val returned : Int
)

data class Stories (

        val available : Int,
        val collectionURI : String,
        val items : List<Items>,
        val returned : Int
)



data class Items (

        val resourceURI : String,
        val name : String
)


data class Thumbnail (

        val path : String,
        val extension : String
)

data class Urls (

        val type : String,
        val url : String
)

