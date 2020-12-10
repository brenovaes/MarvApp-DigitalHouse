package com.digitalhouse.br.marvelapp.entities.comics

data class ResComics(
        val code : Int,
        val status : String,
        val copyright : String,
        val attributionText : String,
        val attributionHTML : String,
        val etag : String,
        val data : DataCo
)

data class DataCo (

        val offset : Int,
        val limit : Int,
        val total : Int,
        val count : Int,
        val results : ArrayList<ResultsCo>
)
data class ResultsCo (

        val id : Int,
        val digitalId : Int,
        val title : String,
        val variantDescription : String,
        val description : String,
        val modified : String,
        val pageCount : Int,
        val resourceURI : String,
        val urls : ArrayList<UrlsCo>,
        val series : SeriesCo,
        val dates : ArrayList<DatesCo>,
        val prices : ArrayList<PricesCo>,
        val thumbnail : ThumbnailCo,
        val images : ArrayList<ImagesCo>,
        val creators : CreatorsCo,
        val characters : CharactersCo,
        val stories : StoriesCo,
        val events : EventsCo

)

data class UrlsCo (

        val type : String,
        val url : String
)

data class SeriesCo (

        val resourceURI : String,
        val items : ArrayList<Items?>,
        val name : String

)

data class VariantsCo (

        val resourceURI : String,
        val name : String
)

data class DatesCo (

        val type : String,
        val date : String
)

data class PricesCo (

        val type : String,
        val price : Double
)

data class ThumbnailCo (

        val path : String,
        val extension : String
)

data class ImagesCo (
        val path : String,
        val extension : String
)


data class CreatorsCo (

        val available : Int,
        val collectionURI : String,
        val items : ArrayList<Items?>,
        val returned : Int
)

data class CharactersCo (

        val available : Int,
        val collectionURI : String,
        val items : ArrayList<Items?>,
        val returned : Int
)

data class StoriesCo (

        val available : Int,
        val collectionURI : String,
        val items : ArrayList<Items?>,
        val returned : Int
)

data class EventsCo (

        val available : Int,
        val collectionURI : String,
        val items : ArrayList<Items?>,
        val returned : Int
)
data class Items (

        val resourceURI : String,
        val name : String,
        val type : String
)