package com.digitalhouse.br.marvelapp

class Pergunta (val enunciado: String, val alt1: Alternativa, val alt2: Alternativa,
                val alt3: Alternativa, val alt4: Alternativa) {

    override fun toString(): String {
        return "Pergunta(enunciado='$enunciado', alt1=$alt1, alt2=$alt2, alt3=$alt3, alt4=$alt4)"
    }
}