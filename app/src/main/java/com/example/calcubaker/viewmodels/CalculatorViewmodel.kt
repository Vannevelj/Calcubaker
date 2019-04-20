package com.example.calcubaker.viewmodels

import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent
import com.example.calcubaker.models.Metric
import com.example.calcubaker.models.Product
import com.example.calcubaker.services.MetricsService

class CalculatorViewmodel(private val metricsService: MetricsService) {
    var product: Product? = null
    var amount: Double? = null
    var sourceMetric: Metric? = null
    val results: ArrayList<CalculationResult> = arrayListOf()

    fun getMetrics() : List<Metric> = metricsService.getMetrics()
    fun getProducts() : List<Product> = listOf(Product.Generic).plus(metricsService.getProducts().toList())

    fun calculate() {
        if (product == null || amount == null || sourceMetric == null) {
            return
        }

        Answers.getInstance().logCustom(
            CustomEvent("MetricConversion")
                .putCustomAttribute("From", sourceMetric!!.name)
                .putCustomAttribute("Product", product!!.name)
                .putCustomAttribute("Amount", amount.toString()))

        val destinationMetrics = metricsService.getMetrics()

        for(destinationMetric in destinationMetrics)
        {
            val result = metricsService.calculate(amount!!, product!!, sourceMetric!!, destinationMetric);
            results.add(CalculationResult(result, destinationMetric))
        }
    }
}