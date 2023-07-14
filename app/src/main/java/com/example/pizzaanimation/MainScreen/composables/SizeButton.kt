package com.example.pizzaanimation.MainScreen.composables

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pizzaanimation.Model.PizzaSize

@Composable
fun SizeButton(size: PizzaSize, currentSize: PizzaSize, onClickSize: (size: PizzaSize) -> Unit) {
    Button(
        onClick = { onClickSize(size) },
        contentPadding = PaddingValues(0.dp),
        elevation = if (size == currentSize) ButtonDefaults.buttonElevation(10.dp)
        else ButtonDefaults.buttonElevation(0.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = CircleShape,
        modifier = Modifier.size(50.dp)

    ) {
        Text(
            text = when (size) {
                PizzaSize.Small -> "S"
                PizzaSize.Medium -> "M"
                PizzaSize.Large -> "L"
            }, color = Color.Black,
            modifier = Modifier.clip(CircleShape)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    SizeButton(size = PizzaSize.Medium, currentSize = PizzaSize.Medium, onClickSize = {})
}