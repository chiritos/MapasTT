package com.ipn.mapastt.include

import android.graphics.Color
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.ipn.mapastt.R

class MyToolbar {

    companion object{
        fun show(activity: AppCompatActivity,title:String, backButton: Boolean){
            var toolbar = activity.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
            activity.setSupportActionBar(toolbar)
            activity.supportActionBar?.title = title
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(backButton)
        }
    }

}