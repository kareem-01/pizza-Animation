package com.example.pizzaanimation.Model

import com.example.pizzaanimation.R

data class AddOns(
    val name : String,
    val imageId:Int,
    var isSelected:Boolean = false,
    val addOnsGroupId:Int = R.drawable.group_basil
)
