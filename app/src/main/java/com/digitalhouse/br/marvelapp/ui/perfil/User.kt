package com.digitalhouse.br.marvelapp.ui.perfil

import java.io.Serializable

data class User(val id:Int, var username:String, var password: String):Serializable {
    lateinit var email:String
    var pontuacao: String = "0 pts"
    var colocao: String = "0º"

    constructor ( id: Int, username: String, email: String, password: String): this(id, username,password){
        this.email= email
    }
    override fun toString(): String {
        return "User(id=$id, username='$username', email='$email', password='$password')"
    }
}