package com.ipn.mapastt.providers

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ipn.mapastt.Modelo.Client

class ClientProvider {

    private var databaseReference: DatabaseReference= FirebaseDatabase
        .getInstance()
        .reference
        .child("Users")
        .child("Clients")

    fun create(cliente:Client): Task<Void>? {
        return cliente.id?.let { this.databaseReference.child(it).setValue(cliente) }
    }

}