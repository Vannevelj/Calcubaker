package com.example.calcubaker.viewmodels

import com.example.calcubaker.models.Metric
import java.text.DecimalFormat

data class CalculationResult(val amount: Double, val metric:Metric) {
    companion object {
        private val format: DecimalFormat = DecimalFormat("#.##")
    }

    val displayAmount: String = format.format(amount)
}