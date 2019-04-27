package com.calcubaker.Calcubaker.viewmodels

import com.calcubaker.Calcubaker.models.Metric
import java.text.DecimalFormat

data class CalculationResult(val amount: Double, val metric:Metric) {
    companion object {
        private val format: DecimalFormat = DecimalFormat("#.##")
    }

    val displayAmount: String = format.format(amount)
}