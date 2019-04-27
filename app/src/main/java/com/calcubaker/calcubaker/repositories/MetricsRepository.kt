package com.calcubaker.Calcubaker.repositories

import com.calcubaker.Calcubaker.models.Metric
import com.calcubaker.Calcubaker.models.MetricUnit

class MetricsRepository {
    fun getMetrics() : List<Metric> {
        return listOf(
            Metric(MetricUnit.Grams, "Grams (g)"),
            Metric(MetricUnit.Kilogram, "Kilogram (kg)"),
            
            Metric(MetricUnit.Milliliter, "Millilitre (mL)"),
            Metric(MetricUnit.Liter, "Litre (l)"),
            
            Metric(MetricUnit.MetricCups, "Cups (Metric)"),
            Metric(MetricUnit.UsRecipeCups, "Cups (US recipes)"),
            Metric(MetricUnit.USNutritionalCups, "Cups (US nutritional)"),
            
            Metric(MetricUnit.MetricTeaspoons, "Teaspoons (Metric)"),
            Metric(MetricUnit.UsTeaspoons, "Teaspoons (US)"),
            
            Metric(MetricUnit.MetricTablespoons, "Tablespoons (Metric)"),
            Metric(MetricUnit.UsTablespoons, "Tablespoons (US)"),
            
            Metric(MetricUnit.Ounces, "Ounces (oz)"),
            Metric(MetricUnit.UsFluidOunces, "Fluid Ounces (fl. oz)"),
            
            Metric(MetricUnit.Pounds, "Pounds (lbs)"),
            
            Metric(MetricUnit.UKPint, "Pint (UK)"),
            Metric(MetricUnit.USPint, "Pint (US)")
        )
    }
}