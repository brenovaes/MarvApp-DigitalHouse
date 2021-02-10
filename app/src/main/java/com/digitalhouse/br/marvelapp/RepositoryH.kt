package com.digitalhouse.br.marvelapp

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

val db: FirebaseFirestore = FirebaseFirestore.getInstance()
val crH: CollectionReference = db.collection("heroday")
val crFCh: CollectionReference = db.collection("favoviteCh")
val crFCr: CollectionReference = db.collection("favoviteCr")
val crFCo: CollectionReference = db.collection("favoviteCo")
val crTri1: CollectionReference = db.collection("trilha1")
val crPont: CollectionReference = db.collection("pontuacap")


