package com.example.calcubaker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import com.example.calcubaker.repositories.ConversionRepository
import com.example.calcubaker.repositories.MetricsRepository
import com.example.calcubaker.repositories.ProductRepository
import com.example.calcubaker.services.MetricsService
import com.example.calcubaker.viewmodels.CalculatorViewmodel

class MainActivity : AppCompatActivity() {
    private val viewmodel: CalculatorViewmodel = CalculatorViewmodel(
        MetricsService(
            MetricsRepository() ,
            ConversionRepository(),
            ProductRepository()
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun setActionBar(toolbar: Toolbar?) {
        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.title = "Calcubaker"
            supportActionBar.setLogo(R.mipmap.calcubaker_logo)
            supportActionBar.setDisplayShowTitleEnabled(true)
            supportActionBar.setDisplayUseLogoEnabled(true)
            supportActionBar.setDisplayShowHomeEnabled(true)
            supportActionBar.show()
        };
    }
}
