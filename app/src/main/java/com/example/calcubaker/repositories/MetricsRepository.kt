package com.example.calcubaker.repositories

import com.example.calcubaker.models.Metric
import com.example.calcubaker.models.MetricUnit

class MetricsRepository {
    fun getMetrics() : List<Metric> {
        return listOf(
            Metric(MetricUnit.Grams, "Grams (g)"),
            Metric(MetricUnit.Kilogram, "Kilogram (kg)"),
            
            Metric(MetricUnit.Milliliter, "Milliliter (mL)"),
            Metric(MetricUnit.Liter, "Liter (l)"),
            
            Metric(MetricUnit.MetricCups, "Cups (Metric)"),
            Metric(MetricUnit.UsRecipeCups, "Cups (US recipes)"),
            Metric(MetricUnit.USNutritionalCups, "Cups (US nutritional)"),
            
            Metric(MetricUnit.MetricTeaspoons, "Teaspoons (Metric)"),
            Metric(MetricUnit.UsTeaspoons, "Teaspoons (US)"),
            
            Metric(MetricUnit.MetricTablespoons, "Tablespoons (Metric)"),
            Metric(MetricUnit.UsTablespoons, "Tablespoons (US)"),
            
            Metric(MetricUnit.Ounces, "Ounces (oz)"),
            Metric(MetricUnit.UsFluidOunces, "Fluid ounces (fl. oz)"),
            
            Metric(MetricUnit.Pounds, "Pounds (lbs)"),
            
            Metric(MetricUnit.UKPint, "Pint (UK)"),
            Metric(MetricUnit.USPint, "Pint (US)")
        )
    }
}