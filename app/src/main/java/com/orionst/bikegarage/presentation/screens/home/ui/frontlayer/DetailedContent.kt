package com.orionst.bikegarage.presentation.screens.home.ui.frontlayer

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.orionst.bikegarage.presentation.screens.home.entity.BikeDetailsNavigationItem
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalPagerApi
@Composable
fun DetailedContent(
    tabs: List<BikeDetailsNavigationItem>,
    currentScreen: BikeDetailsNavigationItem,
    onTabSelected: (BikeDetailsNavigationItem) -> Unit,
    onAddComponent: () -> Unit,
    onAddRide: () -> Unit,
    pagerState: PagerState,
    content: @Composable () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        Column {
            TabRow(
                allScreens = tabs,
                currentScreen = currentScreen,
                pagerState = pagerState,
                onTabSelected = onTabSelected,
            )
            TabsContent(tabs = tabs, pagerState = pagerState, content = content)
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomEnd),
            visible = !pagerState.isScrollInProgress,
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = when (currentScreen) {
                    is BikeDetailsNavigationItem.Components -> onAddComponent
                    is BikeDetailsNavigationItem.Rides -> onAddRide
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = currentScreen.fabTextResId)
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabRow(
    allScreens: List<BikeDetailsNavigationItem>,
    pagerState: PagerState,
    currentScreen: BikeDetailsNavigationItem,
    onTabSelected: (BikeDetailsNavigationItem) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.primary,
        ),
    ) {
        val scope = rememberCoroutineScope()
        Row(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colors.surface,
                            MaterialTheme.colors.primary.copy(alpha = 0.5f),
                            MaterialTheme.colors.surface,
                        )
                    )
                )
                .selectableGroup()
                .fillMaxWidth(),
        ) {
            allScreens.forEachIndexed() { index, screen ->
                Tab(
                    text = stringResource(screen.titleResId),
                    icon = painterResource(screen.icon),
                    onSelected = {
                        scope.launch {
                            onTabSelected(screen)
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    selected = currentScreen == screen
                )
            }
        }
    }
}

@Composable
private fun RowScope.Tab(
    text: String,
    icon: Painter,
    onSelected: () -> Unit,
    selected: Boolean
) {
    val color = MaterialTheme.colors.onSurface
    val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )

    Row(
        modifier = Modifier
            .weight(1f)
            .animateContentSize()
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .clearAndSetSemantics { contentDescription = text }
            .padding(16.dp),
    ) {
        Icon(painter = icon, contentDescription = text, tint = tabTintColor)
        if (selected) {
            Spacer(Modifier.width(12.dp))
            Text(
                text = text.uppercase(Locale.getDefault()),
                color = tabTintColor
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    tabs: List<BikeDetailsNavigationItem>,
    pagerState: PagerState,
    content: @Composable () -> Unit
) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        //tabs[page].screen()
        content()
    }
}

private const val InactiveTabOpacity = 0.60f

private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100
