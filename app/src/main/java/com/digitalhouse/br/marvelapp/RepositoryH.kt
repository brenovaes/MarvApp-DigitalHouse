package com.digitalhouse.br.marvelapp

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


val db: FirebaseFirestore = FirebaseFirestore.getInstance()
val crH: CollectionReference = db.collection("heroday")