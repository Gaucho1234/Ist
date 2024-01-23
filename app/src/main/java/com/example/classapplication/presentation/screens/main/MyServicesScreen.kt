package com.example.classapplication.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.navigation.NavController
import com.example.classapplication.R
import com.example.classapplication.presentation.MainViewModel


@Composable
fun MyServicesScreen(navController: NavController,vm: MainViewModel) {
    val userData = vm.userData.value
    val isLoading = vm.inProgress.value


    BottomNavigationMenu(selectedItem = BottomNavigationItem.SERVICES, navController = navController)


}