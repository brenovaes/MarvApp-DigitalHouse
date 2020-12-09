package com.digitalhouse.br.marvelapp.models

import java.io.Serializable

data class Characters(var id: Int, var imagemCharacter: Int, var nomeCharacter: String): EntesMarvel(id, nomeCharacter, imagemCharacter),Serializable

