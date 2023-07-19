package com.example.pizzaanimation.Model

import com.example.pizzaanimation.R
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
        name = basil,
        imageId = R.drawable.basil_1,
        addOnsGroupId = R.drawable.basil_group
    ),
    AddOns(
        name = onions,
        imageId = R.drawable.onion_1,
        addOnsGroupId = R.drawable.onion_group
    ),
    AddOns(
        name = broccoli,
        imageId = R.drawable.broccoli_1,
        addOnsGroupId = R.drawable.broccoli_group
    ),
    AddOns(
        name = mushrooms,
        imageId = R.drawable.mushroom_1,
        addOnsGroupId = R.drawable.mushroom_group
    ),
    AddOns(
        name = sausages,
        imageId = R.drawable.sausage_1,
        addOnsGroupId = R.drawable.suasage_group
    ),
)