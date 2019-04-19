package com.example.calcubaker

import com.example.calcubaker.models.MetricUnit
import com.example.calcubaker.models.Product
import com.example.calcubaker.repositories.ConversionRepository
import com.example.calcubaker.repositories.MetricsRepository
import com.example.calcubaker.repositories.ProductRepository
import com.example.calcubaker.services.MetricsService
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

@RunWith(Parameterized::class)
class CalculationTests(private val amount: Double, private val fromUnit: MetricUnit, private val toUnit: MetricUnit, private val expected: Double) {

    private val service = MetricsService(MetricsRepository(), ConversionRepository(), ProductRepository())

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.Milliliter, 100.0),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.Kilogram, 0.100),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.Liter, 0.100),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.MetricCups, 0.400),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.USNutritionalCups, 0.4166666667),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.UsRecipeCups, 0.4226721332),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.MetricTeaspoons, 20.0),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.UsTeaspoons, 20.2884136211),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.MetricTablespoons, 6.6666666667),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.UsTablespoons, 6.7628045404),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.Pounds, 0.220462442),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.Ounces, 3.5278470608),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.UsFluidOunces, 3.3814022702),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.UKPint, 0.1706484642),
            arrayOf(100.0, MetricUnit.Grams, MetricUnit.USPint, 0.2114164905)
        )
    }

    @Test
    fun shouldCalculateTheCorrectConversion() {
        val from = service.getMetric(fromUnit)
        val to = service.getMetric(toUnit)

        val result = service.calculate(amount, Product.Generic, from, to)
        val formattedResult = java.lang.Double.valueOf(DecimalFormat("#.##########", DecimalFormatSymbols(Locale.UK)).format(result))

        assertThat(expected, equalTo(formattedResult))
    }
}