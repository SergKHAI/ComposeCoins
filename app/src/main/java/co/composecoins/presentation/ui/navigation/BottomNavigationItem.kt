package co.composecoins.presentation.ui.navigation

import co.composecoins.R

sealed class BottomNavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : BottomNavigationItem("home", R.drawable.ic_home, "Home")
    object Profile : BottomNavigationItem("profile", R.drawable.ic_account, "Profile")
}
sealed class NavItem(var route: String){
    object Detail : NavItem("detail")
}