package com.ipn.mapastt

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    private lateinit var mTextInputEmail: TextView
    private lateinit var mTextInputPassword: TextView
    private lateinit var mButtonLogin:Button

    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        FirebaseApp.initializeApp(this);

        mButtonLogin=findViewById<Button>(R.id.btn_loginuser)
        mTextInputEmail=findViewById<TextView>(R.id.txt_inputemail)
        mTextInputPassword=findViewById<TextView>(R.id.txt_password)

        mFirebaseAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference

        mButtonLogin.setOnClickListener {
            this.login()
        }
    }

    private fun login() {
        val email  = this.mTextInputEmail.text.toString()
        val password = this.mTextInputPassword.text.toString()

        if (!email.isEmpty() && !password.isEmpty()){
            if(password.length > 6){
                mFirebaseAuth
                    .signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener {
                        OnCompleteListener<AuthResult> {
                            if(it.isSuccessful){
                                Toast.makeText(this,
                                    "El login se realizo exitosamente",
                                    Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(this,
                                    "La contreaseña o el password es incorrecto",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            }
        }else{
            Toast.makeText(this,"La contraseña y el email son obligaotrios",Toast.LENGTH_LONG).show()
        }
    }

}