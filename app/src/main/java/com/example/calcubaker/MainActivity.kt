package com.example.calcubaker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toolbar
import com.example.calcubaker.models.Metric
import com.example.calcubaker.repositories.ConversionRepository
import com.example.calcubaker.repositories.MetricsRepository
import com.example.calcubaker.repositories.ProductRepository
import com.example.calcubaker.services.MetricsService
import com.example.calcubaker.viewmodels.CalculatorViewmodel

class MainActivity : AppCompatActivity(), OnItemSelectedListener {
    private val viewmodel: CalculatorViewmodel = CalculatorViewmodel(
        MetricsService(
            MetricsRepository() ,
            ConversionRepository(),
            ProductRepository()
        )
    )

    private lateinit var sourceMetrics: Spinner
    private lateinit var products: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        sourceMetrics = findViewById(R.id.sourceMetric)
        products = findViewById(R.id.product)

        loadMetrics()
        loadProducts()
    }

    private fun loadMetrics() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, viewmodel.getMetrics().map { metric -> metric.name })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sourceMetrics.adapter = adapter
    }

    private fun loadProducts() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, viewmodel.getProducts().map { product -> product.name })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        products.adapter = adapter
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (view == sourceMetrics) {
            val metric = viewmodel.getMetrics().first { metric -> metric.name == sourceMetrics.getItemAtPosition(position)}
            viewmodel.sourceMetric = metric
        } else if (view == products) {
            val product = viewmodel.getProducts().first { product -> product.name == products.getItemAtPosition(position)}
            viewmodel.product = product
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}
