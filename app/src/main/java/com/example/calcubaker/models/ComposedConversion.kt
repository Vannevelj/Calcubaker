package com.calcubaker.calcubaker.models

class ComposedConversion(from: MetricUnit, to: MetricUnit, private val conversions: List<Conversion>) : Conversion(from, to, null) {
    override fun calculate(amount: Double, product: Product): Double {
        var result: Double = amount

        conversions.forEach { conversion ->
            result = conversion.calculate(result, product);
        }

        return result;
    }
}