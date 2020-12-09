package com.digitalhouse.br.marvelapp.models

import java.io.Serializable

data class Creators (var id: Int, var imagemCriador: Int, var nomeCriador: String, var funcaoCriador: String): EntesMarvel(id, nomeCriador, imagemCriador),Serializable

