package com.orionst.bikegarage.presentation.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.orionst.bikegarage.R
import com.orionst.bikegarage.presentation.entity.BikeUi
import com.orionst.bikegarage.presentation.screens.home.entity.BikeDetailsNavigationItem
import com.orionst.bikegarage.presentation.screens.home.ui.backlayer.BackLayerContent
import com.orionst.bikegarage.presentation.screens.home.ui.frontlayer.DetailedContent
import com.orionst.bikegarage.presentation.screens.home.ui.frontlayer.LoadingContent
import com.orionst.bikegarage.presentation.screens.home.ui.frontlayer.WelcomeContent
import com.orionst.bikegarage.presentation.screens.home.ui.frontlayer.tabs.ComponentsScreen
import com.orionst.bikegarage.presentation.screens.home.ui.frontlayer.tabs.RidesScreen
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onAddBike: () -> Unit,
    onAddComponent: (BikeUi) -> Unit,
    onAddRide: (BikeUi) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
        onAddBike = onAddBike,
        onBikeItemClick = viewModel::onBikeListItemClick,
        onEditBike = viewModel::onEditBikeClick,
        onDeleteBike = viewModel::onDeleteBikeClick,
        onAddComponent = onAddComponent,
        onAddRide = onAddRide,
    )
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
private fun HomeScreen(
    uiState: HomeScreenState,
    onAddBike: () -> Unit,
    onBikeItemClick: (BikeUi) -> Unit,
    onEditBike: (BikeUi) -> Unit,
    onDeleteBike: (BikeUi) -> Unit,
    onAddComponent: (BikeUi) -> Unit,
    onAddRide: (BikeUi) -> Unit,
) {
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    val scope = rememberCoroutineScope()

    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = {
            ScreenTopAppBar(scaffoldState = scaffoldState, uiState = uiState)
        },
        backLayerContent = {
            BackLayerContent(
                uiState = uiState,
                onAddBike = onAddBike,
                onSelectBikeItem = {
                    scope.launch {
                        scaffoldState.conceal()
                        onBikeItemClick(it)
                    }
                },
                onEditBike = { onEditBike(it) },
                onDeleteBike = { onDeleteBike(it) },
            )
        },
        frontLayerContent = {
            when (uiState) {
                HomeScreenState.Loading -> {
                    LoadingContent()
                }
                HomeScreenState.EmptyGarage -> {
                    WelcomeContent { onAddBike() }
                }
                is HomeScreenState.Garage -> {
                    var currentScreen: BikeDetailsNavigationItem by remember {
                        mutableStateOf(BikeDetailsNavigationItem.Components)
                    }
                    val pagerState = rememberPagerState()
                    DetailedContent(
                        tabs = tabsList,
                        currentScreen = currentScreen,
                        onTabSelected = { currentScreen = it },
                        onAddComponent = { onAddComponent(uiState.selectedBike) },
                        onAddRide = { onAddRide(uiState.selectedBike) },
                        pagerState = pagerState,
                        content = {
                            when (tabsList[pagerState.currentPage]) {
                                BikeDetailsNavigationItem.Components -> ComponentsScreen()
                                BikeDetailsNavigationItem.Rides -> RidesScreen()
                            }
                        }
                    )
                }
            }
        }
    )
}

private val tabsList = listOf(BikeDetailsNavigationItem.Components, BikeDetailsNavigationItem.Rides)

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ScreenTopAppBar(
    scaffoldState: BackdropScaffoldState,
    uiState: HomeScreenState,
) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        title = { TitleContent(uiState) },
        navigationIcon = {
            if (scaffoldState.isConcealed) {
                IconButton(
                    onClick = { scope.launch { scaffoldState.reveal() } }
                ) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
            } else {
                IconButton(
                    onClick = { scope.launch { scaffoldState.conceal() } }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
            }
        },
        elevation = 0.dp,
        backgroundColor = Color.Transparent
    )
}

@Composable
private fun TitleContent(uiState: HomeScreenState) {
    when (uiState) {
        HomeScreenState.EmptyGarage -> {
            Text(
                modifier = Modifier.fillMaxWidth(fraction = 0.8f),
                text = stringResource(id = R.string.app_name),
            )
        }
        HomeScreenState.Loading -> {
            LinearProgressIndicator(color = MaterialTheme.colors.onPrimary)
        }
        is HomeScreenState.Garage -> {
            Crossfade(targetState = uiState.selectedBike.fullName) { state ->
                Text(
                    modifier = Modifier.fillMaxWidth(fraction = 0.8f),
                    text = state,
                )
            }
        }
    }
}

@Preview(name = "Loading")
@Composable
private fun HomeScreenLoadingPreview() {
    MaterialTheme {
        HomeScreen(
            uiState = HomeScreenState.Loading,
            onAddBike = {},
            onBikeItemClick = {},
            onEditBike = {},
            onDeleteBike = {},
            onAddComponent = {},
            onAddRide = {},
        )
    }
}

@Preview(name = "Empty")
@Composable
private fun HomeScreenEmptyPreview() {
    MaterialTheme {
        HomeScreen(
            uiState = HomeScreenState.EmptyGarage,
            onBikeItemClick = {},
            onAddBike = {},
            onEditBike = {},
            onDeleteBike = {},
            onAddComponent = {},
            onAddRide = {},
        )
    }
}

@Preview(name = "Bikes List")
@Composable
private fun HomeScreenListPreview() {
    MaterialTheme {
        val bikes = listOf(
            BikeUi(
                uid = 1,
                brandName = "Merida",
                modelName = "Ninety-Six RC XT",
                description = "My lovely full suspension cross-country bike."
            ),
            BikeUi(
                uid = 2,
                brandName = "Marin",
                modelName = "Palisades Trail",
                description = "The best of Marine's bikes"
            )
        )

        HomeScreen(
            uiState = HomeScreenState.Garage(
                bikes = bikes,
                selectedBike = bikes.first()
            ),
            onBikeItemClick = {},
            onAddBike = {},
            onEditBike = {},
            onDeleteBike = {},
            onAddComponent = {},
            onAddRide = {},
        )
    }
}
