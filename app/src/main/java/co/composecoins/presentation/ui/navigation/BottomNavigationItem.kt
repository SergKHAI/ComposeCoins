package co.composecoins.presentation.ui.navigation

import co.composecoins.R

sealed class BottomNavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : BottomNavigationItem("home", R.drawable.ic_home, "Home")
    object Favorites : BottomNavigationItem("favorites", R.drawable.ic_favorite, "Favorites")
}
sealed class NavItem(var route: String){
    object Detail : NavItem("detail")
}