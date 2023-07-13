package com.example.pizzaanimation.MainScreen.composables

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pizzaanimation.Model.PizzaSize

@Composable
fun SizeButton(size: PizzaSize, onClickSize: (size: PizzaSize) -> Unit) {
    Button(onClick = { onClickSize(size) }, contentPadding = PaddingValues(0.dp)) {
        Text(
            text = when (size) {
                PizzaSize.Small -> "S"
                PizzaSize.Medium -> "M"
                PizzaSize.Large -> "L"
            }, Modifier
        )
    }
}