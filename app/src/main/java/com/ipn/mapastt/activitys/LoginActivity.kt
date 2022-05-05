package com.ipn.mapastt.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ipn.mapastt.LoadingDialog
import com.ipn.mapastt.R
import com.ipn.mapastt.activitys.client.MapClientActivity
import com.ipn.mapastt.activitys.driver.MapDriverActivity
import com.ipn.mapastt.include.MyToolbar

class LoginActivity : AppCompatActivity() {

    private lateinit var mTextInputEmail: TextView
    private lateinit var mTextInputPassword: TextView
    private lateinit var mButtonLogin:Button

    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var mDatabase : DatabaseReference
    private lateinit var mDialog: LoadingDialog
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        FirebaseApp.initializeApp(this)

        mButtonLogin=findViewById(R.id.btn_loginuser)
        mTextInputEmail=findViewById(R.id.txt_inputemail)
        mTextInputPassword=findViewById(R.id.txt_password)

        mFirebaseAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference

        MyToolbar.show(this,"Login",true)

        this.mDialog = LoadingDialog(this)
        mButtonLogin.setOnClickListener {
            this.login()
        }
    }

    private fun login() {
        val email  = this.mTextInputEmail.text.toString()
        val password = this.mTextInputPassword.text.toString()

        this.mDialog.startAlertDialog()
        if(this.mFirebaseAuth.currentUser!=null) {
            if (!email.isEmpty() && !password.isEmpty()){
                if(password.length>6){
                    mFirebaseAuth
                        .signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener { it ->
                            it.addOnCompleteListener {
                                if(it.isSuccessful){
                                    val shared = this.applicationContext.getSharedPreferences("typeUser", MODE_PRIVATE)
                                    val user= shared.getString("user","never")
                                    if(user.equals("client"))
                                        accessToMap(MapClientActivity())
                                    else
                                        accessToMap(MapDriverActivity())
                                }else{
                                    Toast.makeText(this,
                                        "La contaseña o el password es incorrecto",
                                        Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                }else
                    Toast.makeText(this,"La contraseña debe ser mayor de 6 digitos",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"La contraseña y el email son obligaotrios",Toast.LENGTH_LONG).show()
            }
        }else
            Toast.makeText(this,"La cuenta de usuario es NULL",Toast.LENGTH_LONG).show()
        this.mDialog.dismissDialog()
    }

    fun accessToMap(mapToTypeUser:Activity){
        var intent= Intent(this, mapToTypeUser::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}