package com.calcubaker.calcubaker.models

open class Conversion(public val from: MetricUnit, public val to: MetricUnit, private val calculation: ((amount: Double, product: Product) -> Double)?) {
    open fun calculate(amount: Double, product: Product): Double = calculation?.invoke(amount, product) ?: 0.0

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        val otherConversion = other as Conversion
        if (otherConversion.from == from && otherConversion.to == to) {
            return true
        }

        return false
    }
}