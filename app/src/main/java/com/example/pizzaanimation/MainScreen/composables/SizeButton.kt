package com.example.pizzaanimation.MainScreen.composables

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzaanimation.Model.PizzaSize

@Composable
fun SizeButton(size: PizzaSize, currentSize: PizzaSize, onClickSize: (size: PizzaSize) -> Unit) {
   val  interactionSource = remember { MutableInteractionSource() }


    Button(
        onClick = { onClickSize(size) },
        contentPadding = PaddingValues(0.dp),
        elevation = if (size == currentSize) ButtonDefaults.buttonElevation(0.dp)
        else ButtonDefaults.buttonElevation(0.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = CircleShape,
        modifier = Modifier.size(50.dp)
            .clickable(interactionSource = interactionSource,
            indication = null) {  },
        interactionSource =interactionSource


    ) {
        Text(
            text = when (size) {
                PizzaSize.Small -> "S"
                PizzaSize.Medium -> "M"
                PizzaSize.Large -> "L"
            }, color = Color.Black,
            modifier = Modifier.clip(CircleShape),
            fontSize = 24.sp
        )
    }
}

@Preview
@Composable
private fun Preview() {
    SizeButton(size = PizzaSize.Medium, currentSize = PizzaSize.Medium, onClickSize = {})
}