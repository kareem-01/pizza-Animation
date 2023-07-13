package com.example.pizzaanimation.Model

import com.example.pizzaanimation.R

data class PizzaSelectionUiState(
    val pizzas: List<PizzaUiState> = Pizzas
)


val Pizzas = listOf(
    PizzaUiState(
        breadType = R.drawable.bread_1,
    ),
    PizzaUiState(
        breadType = R.drawable.bread_2,
    ),
    PizzaUiState(
        breadType = R.drawable.bread_3,
    ),
    PizzaUiState(
        breadType = R.drawable.bread_4,
    ),
    PizzaUiState(
        breadType = R.drawable.bread_5,
    ),


    )