package com.example.pizzaanimation.MainScreen.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pizzaanimation.Model.AddOns

@Composable
fun IngredientButton(
    imageId: Int,
    addOnId: Int,
    onIngredientClick: (id: Int) -> Unit,
    isSelected: Boolean = false,
) {
    Box(
        Modifier
            .clip(CircleShape)
            .background(
                color = if (isSelected) Color.Green.copy(alpha = 0.3f) else Color.Transparent
            )
            ,
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clickable { onIngredientClick(addOnId) }
                .padding(8.dp)
        )
    }

}