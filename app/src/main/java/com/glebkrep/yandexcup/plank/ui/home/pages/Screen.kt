package com.glebkrep.yandexcup.plank.ui.home.pages

sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object PlankExercise : Screen("Plank Exercise")
}