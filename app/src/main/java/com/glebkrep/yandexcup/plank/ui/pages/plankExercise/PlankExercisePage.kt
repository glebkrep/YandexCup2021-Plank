package com.glebkrep.yandexcup.plank.ui.pages.plankExercise

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.glebkrep.yandexcup.plank.repository.data.PlankTry
import com.glebkrep.yandexcup.plank.utils.millisToSeconds

@Composable
fun PlankExercisePage(
    onStartNewExercise: () -> (Unit),
    plankExerciseVM: PlankExerciseVM = viewModel()
) {
    val withPadding = Modifier.padding(16.dp)

    var areRulesExpended by remember {
        mutableStateOf(false)
    }
    val tries by plankExerciseVM.tries.observeAsState(listOf())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("Планка!", withPadding)
        }
        item {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    areRulesExpended = !areRulesExpended
                }
            ) {
                Text(
                    text = "Как правильно работать с приложением:",
                    withPadding,
                    color = Color.Blue
                )
                if (tries.isEmpty() || areRulesExpended) {
                    Text(
                        text = "Нажмите на кнопку ниже и установите телефон так, чтобы вас было видно, " +
                                "когда вы делаете упражнение.\n\n" +
                                "Приложение само фиксирует начало и конец попыытки, вам нужно будет только" +
                                " нажать 'завершить тренировку', когда вы устаните и решите закончить",
                        withPadding
                    )
                }
                Button(onClick = {
                    onStartNewExercise.invoke()
                }, withPadding) {
                    Text(text = "Начать новую тренировку")
                }
            }
        }
        item {
            if (tries.isEmpty()) {
                Text(text = "Здесь будет отображен список вашик предыдущих попыток ", withPadding)
            } else {
                Text(text = "Список попыток: ", withPadding)
            }
        }
        items(tries) {
            TryItem(plankTry = it)
        }
    }
}

@Composable
fun TryItem(plankTry: PlankTry) {
    Row(
        Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Black)
            .padding(4.dp)
    ) {
        Text(text = "Попытка ${plankTry.id + 1}", Modifier.padding(8.dp))
        Text(text = plankTry.tryLength.millisToSeconds(), Modifier.padding(8.dp))
    }
}