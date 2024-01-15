package com.example.classapplication.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.classapplication.R
import com.example.classapplication.common.Routes
import com.example.classapplication.presentation.common.navigateTo

enum class BottomNavigationItem(val icon: ImageVector, val navDestination: Routes) {
    SERVICES(Icons.Default.Build, Routes.Services),
    SEARCH(Icons.Default.Search, Routes.Search),
    MYSERVICES(Icons.Default.Settings,Routes.MyServices),
    PROFILE(Icons.Default.Person,Routes.Profile),
}


@Composable
fun BottomNavigationMenu(selectedItem: BottomNavigationItem, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 4.dp)
            .background(Color.White)
    ) {
        for (item in BottomNavigationItem.values()) {
            Image(
                imageVector = item.icon  , contentDescription = null, modifier = Modifier
                    .size(40.dp)
                    .padding(5.dp)
                    .weight(1f)
                    .clickable {
                        navigateTo(navController, item.navDestination)
                    },
                colorFilter = if (item == selectedItem) ColorFilter.tint(Color.Black)
                else ColorFilter.tint(Color.Gray))
//            Image(
//                painter = painterResource(id = item.icon),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(40.dp)
//                    .padding(5.dp)
//                    .weight(1f)
//                    .clickable {
//                        navigateTo(navController, item.navDestination)
//                    },
//                colorFilter = if (item == selectedItem) ColorFilter.tint(Color.Black)
//                else ColorFilter.tint(Color.Gray)
//            )
        }
    }
}