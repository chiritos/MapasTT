package com.ipn.mapastt.activitys.client

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.ipn.mapastt.LoadingDialog
import com.ipn.mapastt.Modelo.Client
import com.ipn.mapastt.databinding.ActivityRegisterBinding
import com.ipn.mapastt.include.MyToolbar
import com.ipn.mapastt.providers.AuthProvider
import com.ipn.mapastt.providers.ClientProvider

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var authProvider: AuthProvider
    private lateinit var clientProvider: ClientProvider

    private lateinit var textInputEmail:TextInputEditText
    private lateinit var textInputPassword:TextInputEditText
    private lateinit var textInputName:TextInputEditText
    private lateinit var btnRegisterUser:Button
    private lateinit var mDialog: LoadingDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.authProvider = AuthProvider()
        this.clientProvider = ClientProvider()

        this.textInputEmail=binding.txtCorreoUser
        this.textInputName=binding.txtNameUser
        this.textInputPassword=binding.txtPasswordUser
        this.btnRegisterUser=binding.btnRegisterUser

        MyToolbar.show(this,"Registro Cliente",true)

        this.mDialog = LoadingDialog(this)
        this.btnRegisterUser.setOnClickListener {
            this.mDialog.startAlertDialog()
            this.registroUser()
        }
    }

    fun registroUser(){
        val name =  textInputName.text.toString()
        val email = textInputEmail.text.toString()
        val password = textInputPassword.text.toString()

        if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            if(password.length>=6){
                this.authProvider.register(email, password)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            val id = FirebaseAuth.getInstance().currentUser?.uid
                            val client = Client(id, name, email)
                            create(client)
                        }else{
                            Toast.makeText(this,"No se pudo registrar el usuario",Toast.LENGTH_SHORT).show()
                            it.exception?.message?.let { it1 -> Log.e("Error", it1) }
                        }
                    }
            }else
                Toast.makeText(this,"La contraseña debe tener almenos 6 caracteres",Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(this,"INGRESE TODOS LOS CAMPOS",Toast.LENGTH_SHORT).show()

        this.mDialog.dismissDialog()
    }

    fun create(client: Client){
        this.clientProvider.create(client)
            ?.addOnCompleteListener {
                if(it.isSuccessful) {
                    var intent= Intent(this, MapClientActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this,"No se puede registrar el usuario",Toast.LENGTH_SHORT).show()
            }
    }
}