package com.example.classapplication.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.classapplication.R
import com.example.classapplication.presentation.MainViewModel


@Composable
fun SplashScreen() {
    Surface {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.my_logo), contentDescription = null,modifier = Modifier
                .width(250.dp)
                .padding(top = 16.dp)
                .padding(8.dp))
        }
    }
}