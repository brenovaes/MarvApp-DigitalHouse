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
val crTri2: CollectionReference = db.collection("trilha2")
val crTri3: CollectionReference = db.collection("trilha3")
val crTri4: CollectionReference = db.collection("trillha4")
val crHank: CollectionReference = db.collection("hanking")
val crPontosTr: CollectionReference = db.collection("pontos_trilhas")


