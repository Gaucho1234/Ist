package com.example.classapplication.presentation.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.classapplication.presentation.OptionsViewModel

@Composable
fun SignUpOptionsScreen() {
    val signUpViewModel: OptionsViewModel = viewModel()

    Column {
        Button(onClick = signUpViewModel::onCustomerSignUp) {
            Text(text = "Sign up as Customer")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = signUpViewModel::onServiceProviderSignUp) {
            Text(text = "Sign up as Service Provider")
        }
    }
}