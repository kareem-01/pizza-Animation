package com.example.pizzaanimation.MainScreen.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzaanimation.Model.PizzaSelectionUiState
import com.example.pizzaanimation.Model.PizzaSize
import com.example.pizzaanimation.Model.Pizzas
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PizzaViewModel : ViewModel() {
    private val _pizzaUiState = MutableStateFlow(PizzaSelectionUiState())
    val pizzaUiState = _pizzaUiState.asStateFlow()

    init {
        viewModelScope.launch {
            changePizzaSize(0, PizzaSize.Medium)

        }
    }

    fun changePizzaSize(pizza: Int, size: PizzaSize) {
        Log.i("Pizza", "${_pizzaUiState.value.pizzas[pizza]}")
        val updatedPizzas = pizzaUiState.value.pizzas.toMutableList()
        updatedPizzas[pizza] = updatedPizzas[pizza].copy(pizzaSize = size)
        _pizzaUiState.update {
            it.copy(pizzas = updatedPizzas)
        }
        Log.i("Pizza", "${_pizzaUiState.value.pizzas[pizza]}")
    }

    fun ingredientClick(pizza: Int, addOn: Int) {
        Log.i("ADDON", _pizzaUiState.value.pizzas[pizza].addOns[addOn].toString())

        _pizzaUiState.update { currentState ->
            val updatedPizzas = currentState.pizzas.toMutableList()
            val pizzaToAddOn = currentState.pizzas[pizza].addOns[addOn]
            val updatedAddOn = pizzaToAddOn.copy(isSelected = !pizzaToAddOn.isSelected)
            updatedPizzas[pizza] = currentState.pizzas[pizza].copy(
                addOns = currentState.pizzas[pizza].addOns.toMutableList().apply {
                    set(addOn, updatedAddOn)
                }
            )

            currentState.copy(pizzas = updatedPizzas)
        }
        Log.i("ADDON", _pizzaUiState.value.pizzas[pizza].addOns[addOn].toString())

    }

}