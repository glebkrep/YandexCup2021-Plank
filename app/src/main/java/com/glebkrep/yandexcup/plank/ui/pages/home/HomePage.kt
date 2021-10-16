package com.glebkrep.yandexcup.plank.ui.pages.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.glebkrep.yandexcup.plank.ui.Screen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomePage(outterNavController: NavController,toCameraActivity:()->(Unit)) {
    val cameraPermissionState =
        rememberPermissionState(android.Manifest.permission.CAMERA)

    Column(Modifier.fillMaxSize()) {

        PermissionRequired(
            permissionState = cameraPermissionState,
            permissionNotGrantedContent = {
                Column {
                    Text("Для работы приложения нужно разрешение на доступ к камере")
                    Row {
                        Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                            Text("Предоставить разрешение!")
                        }
                    }
                }
            },
            permissionNotAvailableContent = {
                Column {
                    Text(
                        "Разрешение не было предоставлено, приложение не сможет работать..."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        ) {
            Text("Разрешение предоставлено")
            Button(onClick = {
                toCameraActivity.invoke()
            }) {
                Text(text = "Начать тренировку")
            }
        }

    }
}