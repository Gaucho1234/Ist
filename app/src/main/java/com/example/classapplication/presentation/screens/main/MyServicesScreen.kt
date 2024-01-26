package com.example.classapplication.presentation.screens.main

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.classapplication.R
import com.example.classapplication.common.Routes
import com.example.classapplication.presentation.MainViewModel
import com.example.classapplication.presentation.common.CommonImage
import com.example.classapplication.presentation.common.ProgressSpinner


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyServicesScreen(navController: NavController,vm: MainViewModel) {

//    val userData = vm.userData.value
//    val isLoading = vm.inProgress.value


//    BottomNavigationMenu(selectedItem = BottomNavigationItem.SERVICES, navController = navController)
    val isLoading = vm.inProgress.value
    if (isLoading) {
        ProgressSpinner()
    } else {
        val serviceData = vm.serviceData.value
        var username by rememberSaveable { mutableStateOf(serviceData?.username ?: "") }
        var userimage by rememberSaveable { mutableStateOf(serviceData?.userImage ?: "") }
        var serviceimage by rememberSaveable { mutableStateOf(serviceData?.serviceImage ?: "") }
        var servicedescription by rememberSaveable { mutableStateOf(serviceData?.serviceDescription ?: "") }
        Log.d("prof", "username: $username, userimage: $userimage, serviceimage: $serviceimage, servicedescription: $servicedescription")
        ServiceContent(
            vm = vm,
            username = username,
            userimage = userimage,
            serviceimage = serviceimage,
            servicedescription = servicedescription,
            onServiceImageChange = { serviceimage = it },
            onServiceDescriptionChange = { servicedescription = it },
            onSave = { vm.updateServiceData(serviceimage, servicedescription, ) },
            onBack = {  navController.navigate(Routes.Services.route) },
            onLogout = {
                vm.onLogout()
                navController.navigate(Routes.Login.route)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceContent(
    vm: MainViewModel,
    userimage: String,
    username: String,
    serviceimage: String,
    servicedescription: String,
    onServiceImageChange: (String) -> Unit,
    onServiceDescriptionChange: (String) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    val scrollState = rememberScrollState()
    val serviceimage = vm.serviceData?.value?.serviceImage
    val userimage = vm.serviceData?.value?.userImage


    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Back", modifier = Modifier.clickable { onBack.invoke() })
            Text(text = "Save", modifier = Modifier.clickable { onSave.invoke() })
        }



        ServiceImage(imageUrl = serviceimage, vm = vm)



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "ServiceDescription", modifier = Modifier.width(100.dp))
            TextField(
                value = servicedescription,
                onValueChange = onServiceDescriptionChange,
                colors = TextFieldDefaults.textFieldColors(

                    textColor = Color.Black
                )
            )
        }

//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 4.dp, end = 4.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(text = "Username", modifier = Modifier.width(100.dp))
//            TextField(
//                value = username,
//                onValueChange = onUsernameChange,
//                colors = TextFieldDefaults.textFieldColors(
//
//                    textColor = Color.Black
//                )
//            )
//        }

//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 4.dp, end = 4.dp),
//            verticalAlignment = Alignment.Top
//        ) {
//            Text(text = "Bio", modifier = Modifier.width(100.dp))
//            TextField(
//                value = bio,
//                onValueChange = onBioChange,
//                colors = TextFieldDefaults.textFieldColors(
//                    textColor = Color.Black
//                ),
//                singleLine = false,
//                modifier = Modifier.height(150.dp)
//            )
//        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Logout", modifier = Modifier.clickable { onLogout.invoke() })
        }
    }
}

@Composable
fun ServiceImage(imageUrl: String?, vm: MainViewModel) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {uri: Uri? ->
        uri?.let {  }
    }

    Box(modifier = Modifier.height(IntrinsicSize.Min)) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable { launcher.launch("image/*") },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp)
            ) {
                CommonImage(data = imageUrl)
            }
            Text(text = "Change profile picture")
        }

        val isLoading = vm.inProgress.value
        if (isLoading)
            ProgressSpinner()
    }


}