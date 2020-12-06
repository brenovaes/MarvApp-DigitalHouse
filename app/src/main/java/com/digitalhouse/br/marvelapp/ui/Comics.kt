package com.digitalhouse.br.marvelapp.ui

import java.io.Serializable


data class Comics(var id: Int, var imagemComic: Int, var nomeComic: String, var dataVendaComic: String, var criadorComic: String):EntesMarvel(id, nomeComic, imagemComic),Serializable

