package com.example.classapplication.common


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.classapplication.presentation.screens.auth.SignupScreen
import com.example.classapplication.presentation.MainViewModel
import com.example.classapplication.presentation.common.NotificationMessage
import com.example.classapplication.presentation.screens.auth.LoginScreen
import com.example.classapplication.presentation.screens.main.MyProfileScreen

import com.example.classapplication.presentation.screens.main.MyServicesScreen
import com.example.classapplication.presentation.screens.main.SearchScreen
import com.example.classapplication.presentation.screens.main.ServiceScreen
import com.example.classapplication.presentation.screens.main.SplashScreen

@Composable
fun DemandApp() {
    val vm: MainViewModel = hiltViewModel()
    val navController = rememberNavController()
    NotificationMessage(vm = vm)
    NavHost(navController = navController, startDestination = Routes.Signup.route) {
        composable(Routes.Profile.route) {
            MyProfileScreen(navController = navController, vm = vm)
        }
        composable(Routes.Signup.route) {
            SignupScreen(navController = navController, vm = vm)
        }
        composable(Routes.Login.route) {
            LoginScreen(navController = navController, vm = vm)
        }
        composable(Routes.Services.route) {
            ServiceScreen(navController = navController, vm = vm)
        }
        composable(Routes.Search.route) {
            SearchScreen(navController = navController, vm = vm)
        }
        composable(Routes.MyServices.route) {
            MyServicesScreen(navController = navController, vm = vm)
        }

        composable(Routes.SplashScreen.route) {
            SplashScreen()
        }

    }
}

