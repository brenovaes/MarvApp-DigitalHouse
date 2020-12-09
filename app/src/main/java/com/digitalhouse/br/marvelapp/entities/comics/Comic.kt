package com.digitalhouse.br.marvelapp.entities.comics

data class ResComics(
        val code : Int,
        val status : String,
        val copyright : String,
        val attributionText : String,
        val attributionHTML : String,
        val etag : String,
        val data : Data
)

data class Data (

        val offset : Int,
        val limit : Int,
        val total : Int,
        val count : Int,
        val results : List<Results>
)
data class Results (

        val id : Int,
        val digitalId : Int,
        val title : String,
        val variantDescription : String,
        val description : String,
        val modified : String,
        val pageCount : Int,
        val resourceURI : String,
        val urls : List<Urls>,
        val series : Series,
        val dates : List<Dates>,
        val prices : List<Prices>,
        val thumbnail : Thumbnail,
        val images : List<Images>,
        val creators : Creators,
        val characters : Characters,
        val stories : Stories,
        val events : Events

)

data class Urls (

        val type : String,
        val url : String
)

data class Series (

        val resourceURI : String,
        val name : String
)

data class Variants (

        val resourceURI : String,
        val name : String
)

data class Dates (

        val type : String,
        val date : String
)

data class Prices (

        val type : String,
        val price : Double
)

data class Thumbnail (

        val path : String,
        val extension : String
)

data class Images (
        val path : String,
        val extension : String
)


data class Creators (

        val available : Int,
        val collectionURI : String,
        val items : List<Items?>,
        val returned : Int
)

data class Characters (

        val available : Int,
        val collectionURI : String,
        val items : List<Items?>,
        val returned : Int
)

data class Stories (

        val available : Int,
        val collectionURI : String,
        val items : List<Items?>,
        val returned : Int
)

data class Events (

        val available : Int,
        val collectionURI : String,
        val items : List<Items?>,
        val returned : Int
)
data class Items (

        val resourceURI : String,
        val name : String,
        val type : String
)