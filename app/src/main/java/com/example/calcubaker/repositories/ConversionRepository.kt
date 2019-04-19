package com.example.calcubaker.repositories

import com.example.calcubaker.models.Conversion
import com.example.calcubaker.models.MetricUnit
import com.example.calcubaker.models.formulas.Formulator

class ConversionRepository {
    fun getConversions() : List<Conversion> {
        return listOf(
            Conversion(MetricUnit.Grams, MetricUnit.UsRecipeCups, { amount, product -> Formulator.volume(amount, product.density) / 236.59 }),
            Conversion(MetricUnit.Grams, MetricUnit.USNutritionalCups, { amount, product -> Formulator.volume(amount, product.density) / 240 }),
            Conversion(MetricUnit.Grams, MetricUnit.MetricCups, { amount, product -> Formulator.volume(amount, product.density) / 250 }),
            Conversion(MetricUnit.UsRecipeCups, MetricUnit.Grams, { amount, product -> Formulator.mass(amount, product.density) * 236.59 }),
            Conversion(MetricUnit.USNutritionalCups, MetricUnit.Grams, { amount, product -> Formulator.mass(amount, product.density) * 240 }),
            Conversion(MetricUnit.MetricCups, MetricUnit.Grams, { amount, product -> Formulator.mass(amount, product.density) * 250 }),
            Conversion(MetricUnit.Milliliter, MetricUnit.Grams, { amount, product -> Formulator.mass(amount, product.density) }),
            Conversion(MetricUnit.Grams, MetricUnit.Milliliter, { amount, product -> Formulator.volume(amount, product.density) }),
            Conversion(MetricUnit.MetricTeaspoons, MetricUnit.Milliliter, { amount, product -> amount * 5 }),
            Conversion(MetricUnit.Milliliter, MetricUnit.MetricTeaspoons, { amount, product -> amount / 5 }),
            Conversion(MetricUnit.UsTeaspoons, MetricUnit.Milliliter, { amount, product -> amount * 4.92892159375 }),
            Conversion(MetricUnit.Milliliter, MetricUnit.UsTeaspoons, { amount, product -> amount / 4.92892159375 }),
            Conversion(MetricUnit.UsTablespoons, MetricUnit.UsTeaspoons, { amount, product -> amount * 3 }),
            Conversion(MetricUnit.UsTeaspoons, MetricUnit.UsTablespoons, { amount, product -> amount / 3 }),
            Conversion(MetricUnit.Milliliter, MetricUnit.Liter, { amount, product -> amount / 1000 }),
            Conversion(MetricUnit.Liter, MetricUnit.Milliliter, { amount, product -> amount * 1000 }),
            Conversion(MetricUnit.Grams, MetricUnit.Kilogram, { amount, product -> amount / 1000 }),
            Conversion(MetricUnit.Kilogram, MetricUnit.Grams, { amount, product -> amount * 1000 }),
            Conversion(MetricUnit.MetricTablespoons, MetricUnit.MetricTeaspoons, { amount, product -> amount * 3 }),
            Conversion(MetricUnit.MetricTeaspoons, MetricUnit.MetricTablespoons, { amount, product -> amount / 3 }),
            Conversion(MetricUnit.Ounces, MetricUnit.Grams, { amount, product -> amount * 28.3459 }),
            Conversion(MetricUnit.Grams, MetricUnit.Ounces, { amount, product -> amount / 28.3459 }),
            Conversion(MetricUnit.Pounds, MetricUnit.Grams, { amount, product -> amount * 453.592 }),
            Conversion(MetricUnit.Grams, MetricUnit.Pounds, { amount, product -> amount / 453.592 }),
            Conversion(MetricUnit.UsFluidOunces, MetricUnit.UsTeaspoons, { amount, product -> amount * 6 }),
            Conversion(MetricUnit.UsTeaspoons, MetricUnit.UsFluidOunces, { amount, product -> amount / 6 }),
            Conversion(MetricUnit.UKPint, MetricUnit.Milliliter, { amount, product -> amount * 586 }),
            Conversion(MetricUnit.Milliliter, MetricUnit.UKPint, { amount, product -> amount / 586 }),
            Conversion(MetricUnit.USPint, MetricUnit.Milliliter, { amount, product -> amount * 473 }),
            Conversion(MetricUnit.Milliliter, MetricUnit.USPint, { amount, product -> amount / 473 })
        )
    }
}