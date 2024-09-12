package com.benayalaskar.musicX.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object Favorite : Screen("favorites")
    object DetailMusik : Screen("home/{musikId}") {
        fun createRoute(musikId: Long) = "home/$musikId"
    }
}