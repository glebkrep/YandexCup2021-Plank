package com.glebkrep.yandexcup.plank.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.glebkrep.yandexcup.plank.R

sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object Camera : Screen("Camera")

}