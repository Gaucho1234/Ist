package com.example.classapplication.presentation.common

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.classapplication.common.Routes
import com.example.classapplication.presentation.MainViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter


@Composable
fun NotificationMessage(vm: MainViewModel) {
    val notifState = vm.popupNotification.value
    val notifMessage = notifState?.getContentOrNull()
    if (notifMessage != null) {
        Toast.makeText(LocalContext.current, notifMessage, Toast.LENGTH_LONG).show()
    }
}

@Composable
fun ProgressSpinner() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.5f)
            .clickable(enabled = false) {}
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator()
    }
}
fun navigateTo(navController: NavController, dest: Routes) {
    navController.navigate(dest.route) {
        popUpTo(dest.route)
        launchSingleTop = true
    }
}
// Check if the user is signed in and navigate to the services screen if they are.
//Removes all composable screens from the backstack.
@Composable
fun CheckSignedIn(vm: MainViewModel, navController: NavController) {
    val alreadyLoggedIn = remember { mutableStateOf(false) }
    val signedIn = vm.signedIn.value
    // If the user is signed in and the user has not already been logged in, navigate to the services screen.
    if (signedIn && !alreadyLoggedIn.value) {
        alreadyLoggedIn.value = true
        navController.navigate(Routes.Services.route) {
            popUpTo(0)
        }
    }
}

@Composable
fun <T> DropdownField(
    selectedOption: T,
    onValueChange: (T) -> Unit,
    options: List<T>,
    label: @Composable (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    Column {
        // Clickable area to toggle dropdown
        Box(
            modifier = Modifier.clickable { expanded = true }
        ) {
            // Display selected option
            label(options[selectedIndex])
        }

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(text = { /*TODO*/ }, onClick = {
                    selectedIndex = index
                    expanded = false
                    onValueChange(option)
                })
                label(option)
            }
        }
    }
}



@OptIn(ExperimentalCoilApi::class)
@Composable
fun CommonImage(
    data: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    val painter = rememberAsyncImagePainter(model = data)
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale
    )
    if (painter.state is AsyncImagePainter.State.Loading) {
        ProgressSpinner()
    }
}