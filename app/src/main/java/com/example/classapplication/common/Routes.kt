package com.example.classapplication.common

sealed class Routes(val route:String){
    object Signup:Routes("signup")
}