package com.glebkrep.yandexcup.plank.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.glebkrep.yandexcup.plank.ui.plank.PlankActivity
import com.glebkrep.yandexcup.plank.ui.home.pages.Screen
import com.glebkrep.yandexcup.plank.ui.home.pages.home.HomePage
import com.glebkrep.yandexcup.plank.ui.home.pages.plankExercise.PlankExercisePage
import com.glebkrep.yandexcup.plank.ui.theme.YogaTheme

//Даша знает, что зарядка очень полезна, и выполняет ее исправно каждый день.
//Иногда Даше кажется, что она делает зарядку недостаточно энергично,
//а порой она не успевает прийти в себя после сна. Тогда она не может придумать,
//какие упражнения ей нужно делать. Помогите Даше — напишите приложение,
//которое подсказывает упражнения и определяет, насколько качественно оно было выполнено.
//
//Начать предлагаем с планки, необходимо посчитать время, которое Даша стоит в планке.
//
//Приложение должно:
//- фиксировать визуально или через акселерометр начало и конец упражнения «планка»,
//- фиксировать время стояния "в планке",
//- вести учет попыток и показывать их в списке с возможностью добавить новую попытку.

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YogaTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val mainNavController = rememberNavController()
                    NavHost(
                        navController = mainNavController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(Screen.Home.route) {
                            HomePage() {
                                mainNavController.navigate(Screen.PlankExercise.route)
                            }
                        }
                        composable(Screen.PlankExercise.route) {
                            PlankExercisePage(onStartNewExercise = {
                                startActivity(Intent(this@HomeActivity, PlankActivity::class.java))
                            })
                        }
                    }
                }
            }
        }
    }
}