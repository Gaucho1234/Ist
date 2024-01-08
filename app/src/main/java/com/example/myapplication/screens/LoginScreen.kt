package com.example.myapplication.screens

import OutlinedTextFieldDemo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable

fun LoginScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ){
        Column {
            OutlinedTextFieldDemo(
                fieldTextv= "Enter your name",
                label = "Name"
            )
            Row {
                DynamicButton(
                    text = "Login",
                    onClick = {
                        // Navigate to the user profile screen.
                        navController.navigate("userprofile")
                    }
                )
            }// Create a DynamicButton composable.
        }


    }
    // Create a DynamicButton composable.

}

@Composable
fun DynamicButton(
    text: String,
    onClick: () -> Unit
) {
    // Create a Button composable.
    Button(
        onClick = onClick
    ) {
        // Display the text on the button.
        Text(text = text)
    }
}
