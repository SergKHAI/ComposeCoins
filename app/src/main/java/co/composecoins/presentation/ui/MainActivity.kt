package co.composecoins.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.composecoins.R
import co.composecoins.presentation.ui.navigation.BottomNavigationItem
import co.composecoins.presentation.ui.navigation.NavItem
import co.composecoins.presentation.ui.screens.ProfileScreen
import co.composecoins.presentation.ui.screens.detail.DetailScreen
import co.composecoins.presentation.ui.screens.home.HomeScreen
import co.composecoins.presentation.ui.theme.ComposeCoinsTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.updateCoins()
        setContent {
            ComposeCoinsTheme {
                MainScreen()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Navigation(navController)
    }
}

@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController) {
    val selectedId = remember {
        mutableStateOf("")
    }
    NavHost(navController, startDestination = BottomNavigationItem.Home.route) {
        composable(BottomNavigationItem.Home.route) {
            HomeScreen(onSelectId = {
                selectedId.value = it
                navController.navigate(NavItem.Detail.route)
            })
        }
        composable(BottomNavigationItem.Profile.route) {
            ProfileScreen()
        }
        composable(NavItem.Detail.route) {
            DetailScreen(selectedId.value)
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        backgroundColor = colorResource(id = R.color.black),
        contentColor = Color.White
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Profile
    )
    val checkedButton = remember { mutableStateOf(0) }

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.black)
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.DarkGray,
                alwaysShowLabel = true,
                selected = index == checkedButton.value,
                onClick = {
                    checkedButton.value = index
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}