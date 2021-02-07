package com.digitalhouse.br.marvelapp.models

import com.digitalhouse.br.marvelapp.ui.quiz.Pergunta

data class Trilha (
    var nome:String,
    var qtdPerguntas: Int,
    var listaPerguntas: ArrayList<Pergunta>,
    var pontuacaoPorPergunta: Int,
)