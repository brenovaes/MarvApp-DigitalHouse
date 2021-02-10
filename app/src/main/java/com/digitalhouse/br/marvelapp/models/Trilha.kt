package com.digitalhouse.br.marvelapp.models

import androidx.room.Entity
import com.digitalhouse.br.marvelapp.ui.quiz.Pergunta


data class Trilha (
    var correta:String = " ",
    var errada1:String = " ",
    var errada2:String = " ",
    var errada3:String = " ",
    var pergunta:String = " "

)