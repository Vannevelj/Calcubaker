package com.calcubaker.Calcubaker.models

/***
 * @param density expressed in g/cm3
 */
data class Product(val id: Int, val name: String, val density: Double, val isGeneric: Boolean = false) {
    companion object Instance {
        val Generic = Product(0, "Generic Product", 1.000, true)
    }
}