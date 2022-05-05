package com.ipn.mapastt

import android.app.Activity
import android.app.AlertDialog

class LoadingDialog(private val activity: Activity) {

    private lateinit var dialog: AlertDialog

    fun startAlertDialog(){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.dialog_loadinformation,null))
        builder.setCancelable(false)

        this.dialog = builder.create()
        this.dialog.show()
    }

    fun dismissDialog(){
        this.dialog.dismiss()
    }

}