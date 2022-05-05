package com.ipn.mapastt.providers

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ipn.mapastt.Modelo.Driver

class DriverProvider {

    private var databaseReference: DatabaseReference = FirebaseDatabase
        .getInstance()
        .reference
        .child("Users")
        .child("Drivers")

    fun create(driver: Driver): Task<Void>? {
        return driver.id?.let { this.databaseReference.child(it).setValue(driver) }
    }

}