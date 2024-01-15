package com.example.classapplication.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.classapplication.presentation.MainViewModel



@Composable
fun ServiceScreen(navController: NavController, vm: MainViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Service Screen")


        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.SERVICES,
            navController = navController
        )

    }
}