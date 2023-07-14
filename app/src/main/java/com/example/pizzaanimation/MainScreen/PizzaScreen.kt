package com.example.pizzaanimation.MainScreen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizzaanimation.MainScreen.ViewModel.PizzaViewModel
import com.example.pizzaanimation.MainScreen.composables.HorizontalSpacer
import com.example.pizzaanimation.MainScreen.composables.SizeButton
import com.example.pizzaanimation.Model.PizzaSelectionUiState
import com.example.pizzaanimation.Model.PizzaSize
import com.example.pizzaanimation.R
import com.example.pizzaanimation.ui.theme.space8

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaSelectionScreen(
    viewModel: PizzaViewModel = hiltViewModel(),
) {
    val state by viewModel.pizzaUiState.collectAsState()
    val pagerState = rememberPagerState()
    val pizzaSize by remember {
        mutableStateOf(240)
    }
    PizzaSelectionContent(
        pizzaUiState = state,
        pagerState = pagerState,
        sizeButtonClick = { size: PizzaSize ->
            when (size) {
                PizzaSize.Small -> viewModel.changePizzaSize(
                    pizza = pagerState.currentPage,
                    size = PizzaSize.Small
                )

                PizzaSize.Medium -> viewModel.changePizzaSize(
                    pizza = pagerState.currentPage,
                    size = PizzaSize.Medium
                )

                PizzaSize.Large -> viewModel.changePizzaSize(
                    pizza = pagerState.currentPage,
                    size = PizzaSize.Large
                )
            }
        },
        pizzaSize
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaSelectionContent(
    pizzaUiState: PizzaSelectionUiState,
    pagerState: PagerState,
    sizeButtonClick: (size: PizzaSize) -> Unit,
    pizzaSize: Int,
) {
    val currentSize = pizzaUiState.pizzas[pagerState.currentPage].pizzaSize
    val currentSizeDp = when (currentSize) {
        PizzaSize.Small -> 220.dp
        PizzaSize.Medium -> 240.dp
        PizzaSize.Large -> 260.dp
    }
    val size by animateDpAsState(targetValue = currentSizeDp,
  tween(400, easing = LinearEasing)  )
    LaunchedEffect(key1 = pizzaUiState.pizzas[pagerState.currentPage].pizzaSize) {

    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = space8)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
            Text(text = "Pizza", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.plate),
                contentDescription = null,
                modifier = Modifier
                    .padding(42.dp)
                    .size(350.dp)
            )
            HorizontalPager(pageCount = pizzaUiState.pizzas.size, state = pagerState) { page ->
                Box(
                    Modifier
                        .padding(72.dp)
                        .animateContentSize(
                            animationSpec = SpringSpec(
                                stiffness = Spring.StiffnessLow
                            )
                        )
                        .size(
                          size
                        )
                ) {
                    Image(
                        modifier = Modifier
                            .animateContentSize(
                                animationSpec = SpringSpec(
                                    stiffness = Spring.StiffnessLow
                                )
                            ),
                        painter = painterResource(id = pizzaUiState.pizzas[page].breadType),
                        contentDescription = null,
                    )
                }

            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            SizeButton(
                size = PizzaSize.Small,
                onClickSize = sizeButtonClick,
                currentSize = currentSize
            )
            HorizontalSpacer(spacing = 8.dp)
            SizeButton(
                size = PizzaSize.Medium,
                onClickSize = sizeButtonClick,
                currentSize = currentSize
            )
            HorizontalSpacer(spacing = 8.dp)
            SizeButton(
                size = PizzaSize.Large,
                onClickSize = sizeButtonClick,
                currentSize = currentSize
            )
        }

        Text(text = "Customize Your Pizza")

    }
}


@Preview
@Composable
private fun Preview() {
    PizzaSelectionScreen()
}
