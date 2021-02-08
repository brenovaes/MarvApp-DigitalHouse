package com.digitalhouse.br.marvelapp.models

import androidx.room.Entity
import com.digitalhouse.br.marvelapp.ui.quiz.Pergunta

@Entity(tableName = "trilhas")
data class Trilha (
    var nome:String,
    var qtdPerguntas: Int,
    var listaPerguntas: ArrayList<Pergunta>,
    var pontuacaoPorPergunta: Int,
)