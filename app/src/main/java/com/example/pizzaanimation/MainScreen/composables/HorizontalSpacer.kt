package com.example.pizzaanimation.MainScreen.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun HorizontalSpacer(spacing: Dp) {
    Spacer(modifier = Modifier.width(spacing))
}
