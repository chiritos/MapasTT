package com.ipn.mapastt.activitys.driver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.ipn.mapastt.R
import com.ipn.mapastt.activitys.MainActivity
import com.ipn.mapastt.include.MyToolbar
import com.ipn.mapastt.providers.AuthProvider

class MapDriverActivity : AppCompatActivity() ,OnMapReadyCallback {

    private lateinit var mMap:GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var authprovider: AuthProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_driver)
        MyToolbar.show(this,"Conductor",false)
        this.authprovider = AuthProvider()
        this.mapFragment = supportFragmentManager.findFragmentById(R.id.map_driver) as SupportMapFragment
        this.mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.mMap=googleMap
        this.mMap.mapType=GoogleMap.MAP_TYPE_NORMAL
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.driver_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.action_logout)
            logout()
        return super.onOptionsItemSelected(item)
    }

    fun logout(){
        this.authprovider.logout()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}