package com.example.classapplication.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.navigation.NavController
import com.example.classapplication.R
import com.example.classapplication.presentation.MainViewModel



@Composable
fun ServiceScreen(navController: NavController, vm: MainViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Card() {
                Image( bitmap = ImageBitmap.imageResource(R.drawable.my_logo), contentDescription = null, contentScale = ContentScale.Crop )

            }
            Card() {
                Image( bitmap = ImageBitmap.imageResource(R.drawable.my_logo), contentDescription = null, contentScale = ContentScale.Crop )

            }
        }


        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.SERVICES,
            navController = navController
        )

    }
}