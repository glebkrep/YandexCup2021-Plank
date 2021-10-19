package com.glebkrep.yandexcup.plank.ui.home.pages.plankExercise

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.glebkrep.yandexcup.plank.R
import com.glebkrep.yandexcup.plank.data.PlankTry
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
            Text(stringResource(R.string.plank), withPadding)
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
                    text = stringResource(R.string.guide_header),
                    withPadding,
                    color = Color.Blue
                )
                if (tries.isEmpty() || areRulesExpended) {
                    Text(
                        text = stringResource(id = R.string.app_how_to),
                        withPadding
                    )
                }
                Button(onClick = {
                    onStartNewExercise.invoke()
                }, withPadding) {
                    Text(text = stringResource(R.string.start_new_try))
                }
            }
        }
        item {
            if (tries.isEmpty()) {
                Text(text = stringResource(R.string.tries_list_empty), withPadding)
            } else {
                Text(text = stringResource(R.string.tries_list), withPadding)
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
            .padding(4.dp)
            .border(2.dp, Color.Black)

    ) {
        Text(text = "Попытка ${plankTry.id}", Modifier.padding(8.dp))
        Text(text = plankTry.tryTime.millisToSeconds(), Modifier.padding(8.dp))
    }
}