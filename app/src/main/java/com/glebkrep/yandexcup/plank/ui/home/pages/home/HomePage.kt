package com.glebkrep.yandexcup.plank.ui.home.pages.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.glebkrep.yandexcup.plank.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomePage(toPlankExercise: () -> (Unit)) {
    val cameraPermissionState =
        rememberPermissionState(android.Manifest.permission.CAMERA)

    val withPadding = Modifier.padding(16.dp)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.morning_exercise), withPadding)
        PermissionRequired(
            permissionState = cameraPermissionState,
            permissionNotGrantedContent = {
                Text(
                    stringResource(R.string.permission_explanation),
                    withPadding,
                    textAlign = TextAlign.Center
                )
                Button(onClick = { cameraPermissionState.launchPermissionRequest() }, withPadding) {
                    Text(stringResource(R.string.grant_permission))
                }
            },
            permissionNotAvailableContent = {
                Text(
                    stringResource(R.string.permission_bad),
                    withPadding, textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        ) {
            Button(onClick = {
                toPlankExercise.invoke()
            }, withPadding) {
                Text(text = stringResource(R.string.train_plank))
            }
        }

    }
}