package com.example.pizzaanimation.Model

import com.example.pizzaanimation.ui.theme.basil
import com.example.pizzaanimation.ui.theme.broccoli
import com.example.pizzaanimation.ui.theme.mushrooms
import com.example.pizzaanimation.ui.theme.onions
import com.example.pizzaanimation.ui.theme.sausages

data class PizzaUiState(
    val breadType: Int,
    var pizzaSize: PizzaSize = PizzaSize.Medium,
    val price: Int = 17,
    val addOns: List<AddOns> = pizzaAddOnsList,
)

val pizzaAddOnsList = listOf(
    AddOns(
        name = basil
    ),
    AddOns(
        name = onions
    ),
    AddOns(
        name = broccoli
    ),
    AddOns(
        name = mushrooms
    ),
    AddOns(
        name = sausages
    ),
    )