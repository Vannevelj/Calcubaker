package com.example.calcubaker

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.example.calcubaker.repositories.ConversionRepository
import com.example.calcubaker.repositories.MetricsRepository
import com.example.calcubaker.repositories.ProductRepository
import com.example.calcubaker.services.MetricsService
import com.example.calcubaker.viewmodels.CalculationResult
import com.example.calcubaker.viewmodels.CalculatorViewmodel
import java.lang.Double.parseDouble

class MainActivity : AppCompatActivity(), OnItemSelectedListener, TextWatcher, View.OnClickListener {
    private val viewmodel: CalculatorViewmodel = CalculatorViewmodel(
        MetricsService(
            MetricsRepository() ,
            ConversionRepository(),
            ProductRepository()
        )
    )

    private lateinit var sourceMetrics: Spinner
    private lateinit var products: Spinner
    private lateinit var table: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        sourceMetrics = findViewById(R.id.sourceMetric)
        products = findViewById(R.id.product)
        table = findViewById(R.id.resultsTable)
        table.removeAllViews()

        sourceMetrics.onItemSelectedListener = this
        products.onItemSelectedListener = this
        findViewById<EditText>(R.id.quantity).addTextChangedListener(this)
        findViewById<Button>(R.id.calculateButton).setOnClickListener(this)

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
        reset()

        if (parent == sourceMetrics) {
            val metric = viewmodel.getMetrics().first { metric -> metric.name == sourceMetrics.getItemAtPosition(position)}
            viewmodel.sourceMetric = metric
        } else if (parent == products) {
            val product = viewmodel.getProducts().first { product -> product.name == products.getItemAtPosition(position)}
            viewmodel.product = product
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        reset()
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (p0 == null || p0.isEmpty()) {
            viewmodel.amount = 0.0
        } else {
            viewmodel.amount = parseDouble(p0.toString())
        }
    }

    override fun onClick(v: View?) {
        recalculate()
    }

    private fun reset() {
        table.visibility = View.INVISIBLE
    }

    private fun recalculate() {
        viewmodel.calculate()

        viewmodel.results.forEach { result ->
            var row = table.findViewWithTag<TableRow>(result.metric.id.id)
            if (row == null) {
                row = createRow(result)
                table.addView(row)
            } else {
                val viewGroup = row as ViewGroup
                val valueText = viewGroup.getChildAt(0) as TextView
                valueText.text = result.displayAmount
            }
        }
        table.visibility = View.VISIBLE
    }

    private fun createRow(calculationResult: CalculationResult) : TableRow {
        val row = TableRow(this)
        row.tag = calculationResult.metric.id.id
        val rowLayout = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT)
        rowLayout.topMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,5f, this.resources.displayMetrics).toInt()
        row.layoutParams = rowLayout

        val valueText = TextView(this)
        valueText.text = calculationResult.displayAmount
        valueText.setTypeface(valueText.typeface, Typeface.BOLD)
        valueText.setTextColor(Color.BLACK)
        valueText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        valueText.layoutParams = TableRow.LayoutParams(22, TableRow.LayoutParams.WRAP_CONTENT, 2f)
        val paddingPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,5f, this.resources.displayMetrics).toInt()
        valueText.setPadding(paddingPx, 0, 0, 0)

        val labelText = TextView(this)
        labelText.text = calculationResult.metric.name
        labelText.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 3f)
        valueText.setTextColor(Color.BLACK)

        row.addView(valueText)
        row.addView(labelText)

        return row
    }
}
