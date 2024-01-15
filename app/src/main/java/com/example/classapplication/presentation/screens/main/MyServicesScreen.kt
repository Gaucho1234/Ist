package com.example.classapplication.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.classapplication.presentation.MainViewModel


@Composable
fun MyServicesScreen(navController: NavController, vm: MainViewModel) {
    val userData = vm.userData.value
    val isLoading = vm.inProgress.value
    BottomNavigationMenu(selectedItem = BottomNavigationItem.MYSERVICES, navController = navController)
}