package com.example.calcubaker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Calcubaker"
            actionBar.setLogo(R.mipmap.calcubaker_logo)
            actionBar.setDisplayShowTitleEnabled(true)
            actionBar.setDisplayUseLogoEnabled(true)
        }
    }
}
