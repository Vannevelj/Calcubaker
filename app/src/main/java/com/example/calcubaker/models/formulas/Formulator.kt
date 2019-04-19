package com.example.calcubaker.models.formulas

class Formulator {
    companion object Invoke {
        fun mass(volume: Double, density: Double) : Double {
            return density * volume
        }

        fun volume(mass: Double, density: Double) : Double {
            return mass / density
        }
    }
}