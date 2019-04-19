package com.example.calcubaker.viewmodels

import com.example.calcubaker.models.Metric
import com.example.calcubaker.models.Product
import com.example.calcubaker.services.MetricsService

class CalculatorViewmodel(private val metricsService: MetricsService) {
    var product: Product? = null
    var amount: Double? = null
    var sourceMetric: Metric? = null
    val results: ArrayList<CalculationResult> = arrayListOf()

    fun calculate() {
        if (product == null || amount == null || sourceMetric == null) {
            return
        }

        val destinationMetrics = metricsService.getMetrics().filter { metric -> metric.id != sourceMetric?.id }

        for(destinationMetric in destinationMetrics)
        {
            val result = metricsService.calculate(amount!!, product!!, sourceMetric!!, destinationMetric);
            if (result != null)
            {
                results.add(CalculationResult(amount!!, destinationMetric))
            }
        }
    }
}