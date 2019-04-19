package com.example.calcubaker.models

/***
 * @param density expressed in g/cm3
 */
data class Product(val id: Int, val name: String, val density: Double, val isGeneric: Boolean = false) {
    companion object Instance {
        val Generic = Product(id = 0, name = "Generic Product", density = 1.000, isGeneric = true)
    }
}