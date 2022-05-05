package com.ipn.mapastt.providers

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthProvider {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(email:String,password:String ):Task<AuthResult>{
        return firebaseAuth.createUserWithEmailAndPassword(email,password)
    }

    fun login(email:String,password:String ):Task<AuthResult>{
        return firebaseAuth.signInWithEmailAndPassword(email,password)
    }

    fun logout(){
        this.firebaseAuth.signOut()
    }

}