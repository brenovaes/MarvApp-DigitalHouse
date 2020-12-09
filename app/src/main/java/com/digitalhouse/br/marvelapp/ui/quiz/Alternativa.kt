package com.digitalhouse.br.marvelapp.ui.quiz

import java.io.Serializable

class Alternativa (val opcaoResposta: String, var corBackground: Int): Serializable{
    override fun toString(): String {
        return "Alternativas(opcaoResposta='$opcaoResposta')"
    }
}