package com.calcubaker.calcubaker.models

data class Metric(val id: MetricUnit, val name: String) {
    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        val otherMetric = other as Metric
        if (otherMetric.id == id) {
            return true
        }

        return false
    }
}