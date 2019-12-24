package com.calcubaker.calcubaker.repositories

import com.calcubaker.calcubaker.models.Product

class ProductRepository {
    fun getProducts() : List<Product> {
        return listOf(
            Product(1, "Water", 1.000),
            Product(2, "Sugar (Granulated)", 0.849),
            Product(3, "Sugar (Powdered) ", 0.809),
            Product(4, "Honey", 1.360),
            Product(5, "Salt", 2.160),
            Product(6, "Butter", 0.911),
            Product(7, "Margarine", 0.947),
            Product(8, "Oil (Coconut)", 0.925),
            Product(9, "Oil (Olive)", 0.917),
            Product(10, "Agave nectar", 1.399),
            Product(11, "Syrup (Malt)", 1.420),
            Product(12, "Syrup (Golden)", 1.430),
            Product(13, "Syrup (Maple)", 1.370),
            Product(14, "Yoghurt", 1.030),
            Product(17, "Flour (Corn)", 0.625),
            Product(18, "Flour (Wheat)", 0.593),
            Product(19, "Sugar (Brown)", 0.930),
            Product(21, "Sugar (Demerara)", 0.812),
            Product(23, "Syrup (Corn)", 1.33),
            Product(24, "Milk (Skimmed)", 1.035),
            Product(25, "Milk (Semi skimmed)", 1.030),
            Product(25, "Milk (Whole)", 1.028),
            Product(27, "Cream (Light)", 1.018),
            Product(28, "Cream (Heavy)", 1.005),
            Product(34, "Flour (Almond)", 0.529),
            Product(36, "Flour (Hazelnut)", 0.488),
            Product(37, "Flour (Cake)", 0.423),
            Product(38, "Flour (Bread)", 0.537),
            Product(39, "Flour (Coconut)", 0.473),
            Product(40, "Flour (Walnut)", 0.402),
            Product(41, "Sugar (Caster)", 0.951),
            Product(42, "Baking Powder", 0.933),
            Product(43, "Baking Soda", 2.200),
            Product(44, "Egg White", 1.031),
            Product(45, "Egg Yolk", 1.025),
            Product(46, "Egg", 1.040)
        )
    }
}