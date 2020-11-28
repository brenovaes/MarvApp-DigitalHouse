package com.digitalhouse.br.marvelapp

import java.io.Serializable

class Alternativa (val opcaoResposta: String, var corBackground: Int): Serializable{
    override fun toString(): String {
        return "Alternativas(opcaoResposta='$opcaoResposta')"
    }
}