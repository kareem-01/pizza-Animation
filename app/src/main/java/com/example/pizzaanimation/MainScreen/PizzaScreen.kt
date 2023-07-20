package com.example.pizzaanimation.MainScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizzaanimation.MainScreen.ViewModel.PizzaViewModel
import com.example.pizzaanimation.MainScreen.composables.HorizontalSpacer
import com.example.pizzaanimation.MainScreen.composables.IngredientButton
import com.example.pizzaanimation.MainScreen.composables.SizeButton
import com.example.pizzaanimation.Model.PizzaSelectionUiState
import com.example.pizzaanimation.Model.PizzaSize
import com.example.pizzaanimation.R
import com.example.pizzaanimation.ui.theme.Brown
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
        pizzaSize = pizzaSize,
        onIngredientClick = { addOnInex ->
            viewModel.ingredientClick(pizza = pagerState.currentPage, addOn = addOnInex)
        }
    )

}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun PizzaSelectionContent(
    pizzaUiState: PizzaSelectionUiState,
    pagerState: PagerState,
    sizeButtonClick: (size: PizzaSize) -> Unit,
    pizzaSize: Int,
    onIngredientClick: (id: Int) -> Unit,
) {
    val currentSize = pizzaUiState.pizzas[pagerState.currentPage].pizzaSize
    val currentSizeScale = when (currentSize) {
        PizzaSize.Small -> .8f
        PizzaSize.Medium -> .9f
        PizzaSize.Large -> 1f
    }
    val size by animateFloatAsState(
        targetValue = currentSizeScale,
        tween(350, easing = LinearEasing)
    )
    val offset by animateDpAsState(
        targetValue = when (currentSize) {
            PizzaSize.Small -> (-60).dp
            PizzaSize.Medium -> 0.dp
            PizzaSize.Large -> 60.dp
        },
        tween(500)
    )
    LaunchedEffect(key1 = pizzaUiState.pizzas[pagerState.currentPage].pizzaSize) {

    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = space8)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 16.dp),
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
                        .scale(size),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .animateContentSize(
                                animationSpec = SpringSpec(
                                    stiffness = Spring.StiffnessLow
                                )
                            ),
                        painter = painterResource(id = pizzaUiState.pizzas[page].breadType),
                        contentDescription = null,
                    )
                    pizzaUiState.pizzas[pagerState.currentPage].addOns.forEach { addOn ->
                        androidx.compose.animation.AnimatedVisibility(
                            visible = addOn.isSelected && !pagerState.isScrollInProgress,
                            enter = scaleIn(initialScale = 50f) + fadeIn(),
                            exit = ExitTransition.None
                        ) {
                            Image(
                                painter = painterResource(id = addOn.addOnsGroupId),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }

        Text(
            text = '$' + pizzaUiState.pizzas[pagerState.currentPage].price.toString(),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Box(modifier = Modifier) {
            Card(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
                    .offset(x=offset),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(10.dp)

            ) {
            }
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SizeButton(
                    size = PizzaSize.Small,
                    onClickSize = sizeButtonClick,
                    currentSize = currentSize
                )
                SizeButton(
                    size = PizzaSize.Medium,
                    onClickSize = sizeButtonClick,
                    currentSize = currentSize
                )
                SizeButton(
                    size = PizzaSize.Large,
                    onClickSize = sizeButtonClick,
                    currentSize = currentSize
                )
            }

        }

        Text(
            text = "Customize Your Pizza",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp),
            fontWeight = FontWeight.SemiBold
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            val addOns = pizzaUiState.pizzas[pagerState.currentPage].addOns
            items(count = addOns.size) { ingredientId ->
                IngredientButton(
                    imageId = addOns[ingredientId].imageId,
                    addOnId = ingredientId,
                    onIngredientClick = onIngredientClick,
                    isSelected = addOns[ingredientId].isSelected
                )
            }
        }

        Button(
            onClick = {}, modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Brown),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(painter = painterResource(id = R.drawable.cart), contentDescription = null)

            Text(text = "Add to Cart", modifier = Modifier.padding(start = 16.dp))
        }

    }
}


@Preview
@Composable
private fun Preview() {
    PizzaSelectionScreen()
}
