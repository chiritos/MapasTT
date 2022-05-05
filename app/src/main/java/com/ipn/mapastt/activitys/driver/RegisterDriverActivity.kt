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
import com.ipn.mapastt.Modelo.Driver
import com.ipn.mapastt.activitys.SelectOptionAuthActivity
import com.ipn.mapastt.activitys.driver.MapDriverActivity
import com.ipn.mapastt.databinding.ActivityRegisterDriverBinding
import com.ipn.mapastt.include.MyToolbar
import com.ipn.mapastt.providers.AuthProvider
import com.ipn.mapastt.providers.DriverProvider

class RegisterDriverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterDriverBinding

    private lateinit var authProvider: AuthProvider
    private lateinit var driverProvider: DriverProvider

    private lateinit var textInputEmail:TextInputEditText
    private lateinit var textInputPassword:TextInputEditText
    private lateinit var textInputName:TextInputEditText
    private lateinit var textInputMarca:TextInputEditText
    private lateinit var textInputPlaca:TextInputEditText

    private lateinit var btnRegisterUser:Button

    private lateinit var mDialog: LoadingDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterDriverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.authProvider = AuthProvider()
        this.driverProvider = DriverProvider()

        this.textInputEmail=binding.txtCorreoUser
        this.textInputName=binding.txtNameUser
        this.textInputPassword=binding.txtPasswordUser
        this.textInputMarca=binding.txtMarcaCarro
        this.textInputPlaca=binding.txtPlacaCarro
        this.btnRegisterUser=binding.btnRegisterUser

        MyToolbar.show(this,"Registro Conductor",true)

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
        val marca = textInputMarca.text.toString()
        val placa = textInputPlaca.text.toString()

        if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !marca.isEmpty() && !placa.isEmpty()){
            if(password.length>=6){
                this.authProvider.register(email, password)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            val id = FirebaseAuth.getInstance().currentUser?.uid
                            val driver = Driver(id, name, email,marca,placa)
                            create(driver)
                        }
                        else{
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

    fun create(driver: Driver){
        this.driverProvider.create(driver)
            ?.addOnCompleteListener {
                if(it.isSuccessful){
                    var intent=Intent(this, MapDriverActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this,"No se puede registrar el usuario",Toast.LENGTH_SHORT).show()
        }
    }

}